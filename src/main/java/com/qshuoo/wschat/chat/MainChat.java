package com.qshuoo.wschat.chat;


import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.qshuoo.wschat.config.ApplicationContextRegister;
import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.service.MessageService;
import com.qshuoo.wschat.utils.ChatsPool;
import com.qshuoo.wschat.utils.MsgUtils;


/**
 * 与客户端的连接
 * @author qiaoyongshuo
 *
 */
@Component
@Lazy(true) // 需要在ApplicationContextRegister注册后加载
@ServerEndpoint(value = "/websocket/{userAccount}")
public class MainChat {
	
	private static Logger logger = LoggerFactory.getLogger(MainChat.class);

	// TODO WebSocket @Autowired 注入Bean失败 暂时用ApplicationContext注入
	private MessageService service = (MessageService) ApplicationContextRegister.getApplicationContext().getBean("MessageService");

    /**
     * 连接建立成功调用的方法
     * @throws Exception 
     */
    @OnOpen
    public void onOpen(@PathParam("userAccount") Long account, Session session) throws Exception{
        ChatsPool.addSession(account, session);
        logger.info("user {} is connected", account);
        // 读取离线消息  
        List<String> result = service.getOffLineMsgs(account);
        if (result == null) {
        	return;
        }
        // TODO  接收消息
        for (String string : result) {
			session.getBasicRemote().sendText(string);
		}
        
        // TODO  更新离线消息为已读
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userAccount") Long account){
    	ChatsPool.removeSession(account);
    	logger.info("user {} is close", account);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @throws Exception 
     */
    @OnMessage
    public void onMessage(String message) throws Exception {
    	// 解析消息
    	Message msg = JSON.parseObject(message, Message.class);
    	if (ChatsPool.containKey(msg.getToUid())) { // 用户在线  -> 发送消息  
    		Session session = ChatsPool.getSession(msg.getToUid());
    		session.getBasicRemote().sendText(MsgUtils.getOnMsgs(msg));
    		// TODO 保存聊天记录
    	} else { // 用户不再线 -> 保存聊天记录
    		service.saveMessage(msg);
    	}
    	
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

}
