package org.caseor.boot.websocket.component;

import lombok.extern.slf4j.Slf4j;
import org.caseor.boot.websocket.entity.User;
import org.caseor.common.util.JsonUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 向具体人推送
 * @author Fu Kai
 * @since 20210121
 */

@Slf4j
@Component
@ServerEndpoint("/websocket/specific")
public class ToSpecificWebsocket {

  /**
   * 记录当前在线连接数
   */
  private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

  /**
   * 存放所有在线的客户端
   */
  private static final Map<String, Session> CLIENTS = new ConcurrentHashMap<>();

  /**
   * 连接建立成功调用的方法
   */
  @OnOpen
  public void onOpen(Session session) {
    // 在线数加1
    ONLINE_COUNT.incrementAndGet();
    CLIENTS.put(session.getId(), session);
    log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
  }

  /**
   * 连接关闭调用的方法
   */
  @OnClose
  public void onClose(Session session) {
    // 在线数减1
    ONLINE_COUNT.decrementAndGet();
    CLIENTS.remove(session.getId());
    log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
  }

  /**
   * 收到客户端消息后调用的方法
   * @param message 客户端发送过来的消息
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    log.info("服务端收到客户端[{}]的消息[{}]", session.getId(), message);
    try {
      User user = JsonUtil.deserializeObject(message, User.class);
      if (user != null) {
        Session toSession = CLIENTS.get(user.getId());
        if (toSession != null) {
          this.sendMessage(user.getName(), toSession);
        }
      }
    } catch (Exception e) {
      log.error("解析失败: ", e);
    }
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
    log.info("服务端给客户端[{}]发送消息[{}]", toSession.getId(), message);
    try {
      toSession.getBasicRemote().sendText(message);
    } catch (Exception e) {
      log.error("服务端发送消息给客户端失败: ", e);
    }
  }
}
