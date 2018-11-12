package com.yuanju.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketUtil extends TextWebSocketHandler {
    private Map<String, WebSocketSession> socketCache = new ConcurrentHashMap<>();
    // private static final ArrayList<WebSocketSession> users;//这个会出现性能问题，最好用Map来存储，key用userid
    private static Logger logger = LoggerFactory.getLogger(WebSocketUtil.class); /*static { users = new ArrayList<WebSocketSession>(); }*/

    public WebSocketUtil() {
    }

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connect to the websocket success......当前数量:" + socketCache.size());
        String token = (String) session.getAttributes().get("token");
        socketCache.put(token, session);
        System.out.println("连接后的数量为：" + socketCache.size());
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
        String token = (String) session.getAttributes().get("token");
        System.out.println("用户" + token + "已退出！");
        socketCache.remove(token);
        System.out.println(socketCache.get(token));
        System.out.println("剩余在线用户" + socketCache.size());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.debug("websocket connection closed......");
        String token = (String) session.getAttributes().get("token");
        socketCache.remove(token);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    public boolean send(String token, TextMessage message) {
        if (socketCache.containsKey(token)) {
            return send(socketCache.get(token), message);
        }
        logger.info("socketCache is not key ");
        return false;
    }

    public void sendAll(TextMessage message) {
        if (message == null) return;
        for (WebSocketSession webSocketSession : socketCache.values()) {
            send(webSocketSession, message);
        }
    }

    public boolean send(WebSocketSession webSocketSession, TextMessage message) {
        String token = (String) webSocketSession.getAttributes().get("token");
        if (token == null) {
            return false;
        }
        try {
            webSocketSession.sendMessage(message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("websocket 数据发送异常 !");
            return false;
        }
    }
}
