package org.caseor.boot.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.net.URI;

/**
 * @author Fu Kai
 * @since 20210121
 */

@Slf4j
@Configuration
public class WebsocketConfig {
  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }

  private static final String URI = "ws://localhost:40752/websocket/other";

  /**
   * socket客户端
   */
  @Bean
  public WebSocketClient webSocketClient() {
    WebSocketClient webSocketClient = null;
    try {
      webSocketClient = new WebSocketClient(new URI(URI), new Draft_6455()) {
        @Override
        public void onOpen(ServerHandshake handshake) {
          log.info("[websocket] 连接成功");
        }

        @Override
        public void onMessage(String message) {
          log.info("[websocket] 收到消息={}", message);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
          log.info("[websocket] 退出连接");
        }

        @Override
        public void onError(Exception ex) {
          log.info("[websocket] 连接错误={}", ex.getMessage());
        }
      };
      webSocketClient.connect();
      return webSocketClient;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return webSocketClient;
  }

}
