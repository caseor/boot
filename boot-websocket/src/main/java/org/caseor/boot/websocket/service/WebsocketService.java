package org.caseor.boot.websocket.service;

/**
 * @author Fu Kai
 * @since 20210121
 */

public interface WebsocketService {
    /**
     * 群发
     *
     * @param message
     */
    void groupSending(String message);

    /**
     * 指定发送
     *
     * @param name
     * @param message
     */
    void appointSending(String name, String message);
}
