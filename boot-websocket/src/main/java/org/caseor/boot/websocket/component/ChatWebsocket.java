package org.caseor.boot.websocket.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Fu Kai
 * @since 20210121
 */

@Slf4j
@Component
@ServerEndpoint("/websocket/chat")
public class ChatWebsocket {

  /**
   * 记录当前在线连接数
   */
  private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

  /**
   * 连接建立成功调用的方法
   */
  @OnOpen
  public void onOpen(Session session) {
    // 在线数加1
    ONLINE_COUNT.incrementAndGet();
    log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
  }

  /**
   * 连接关闭调用的方法
   */
  @OnClose
  public void onClose(Session session) {
    // 在线数减1
    ONLINE_COUNT.decrementAndGet();
    log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
  }

  /**
   * 收到客户端消息后调用的方法
   * @param message 客户端发送过来的消息
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
    this.sendMessage("Hello, " + message, session);
  }

  @OnError
  public void onError(Session session, Throwable error) {
    log.error("发生错误");
    error.printStackTrace();
  }

  /**
   * 服务端发送消息给客户端
   */
  private void sendMessage(String message, Session toSession) {
    try {
      log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
      toSession.getBasicRemote().sendText(message);
    } catch (Exception e) {
      log.error("服务端发送消息给客户端失败: ", e);
    }
  }

}
