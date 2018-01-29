package com.qshuoo.wschat.chat;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.alibaba.fastjson.JSON;
import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.utils.ChatsPool;


/**
 * 与客户端的连接
 * @author qiaoyongshuo
 *
 */
@Component
@ServerEndpoint(value = "/websocket/{userAccount}")
public class MainChat {
	
	private static Logger logger = LoggerFactory.getLogger(MainChat.class);


    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("userAccount") Integer account, Session session){
    	logger.info("user {} is connected", account);
        ChatsPool.addSession(account, session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userAccount") Integer account){
    	logger.info("user {} is close", account);
    	ChatsPool.removeSession(account);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @throws IOException 
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
    	Message msg = JSON.parseObject(message, Message.class);
    	Session session = ChatsPool.getSession(msg.getTo());
    	session.getBasicRemote().sendText(msg.getMsg());
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

}
