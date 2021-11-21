package org.caseor.boot.websocket.service.impl;



import org.caseor.boot.websocket.service.WebsocketService;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fu Kai
 * @since 20210121
 */

@Service
public class WebsocketServiceImpl implements WebsocketService {

    @Autowired
    private WebSocketClient webSocketClient;

    @Override
    public void groupSending(String message) {
        webSocketClient.send(message);
    }

    @Override
    public void appointSending(String name, String message) {
        webSocketClient.send("name: " + name + ", message: " + message);
    }
}
