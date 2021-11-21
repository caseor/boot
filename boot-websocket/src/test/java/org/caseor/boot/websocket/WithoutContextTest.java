package org.caseor.boot.websocket;

import org.caseor.common.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author Fu Kai
 * @since 20211120
 */

public class WithoutContextTest {

  @Test
  public void mapStructTest() {
    User user = new User();
    user.setId(1L);
    user.setAge(22);
    user.setName("Fu Kai");
    user.setSex(true);
    user.setBirthday(LocalDateTime.now());
  }

}
