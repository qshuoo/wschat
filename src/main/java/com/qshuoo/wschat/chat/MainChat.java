package com.qshuoo.wschat.chat;


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

	// TODO @ServerEndpoint 暂时未找到支持 @Autowired的方法 
	private MessageService service = (MessageService) ApplicationContextRegister.getApplicationContext().getBean("MessageService");

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userAccount") Long account, Session session){
    	logger.info("user {} is connected", account);
        ChatsPool.addSession(account, session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userAccount") Long account){
    	logger.info("user {} is close", account);
    	ChatsPool.removeSession(account);
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
    	if (ChatsPool.containKey(msg.getToUid())) { // 用户在线  -> 发送消息  -- TODO 保存聊天记录
    		Session session = ChatsPool.getSession(msg.getToUid());
    		session.getBasicRemote().sendText(msg.getMsg());
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
