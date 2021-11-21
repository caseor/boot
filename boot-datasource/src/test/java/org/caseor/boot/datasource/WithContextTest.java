package org.caseor.boot.datasource;

import org.caseor.boot.datasource.entity.User;
import org.caseor.boot.datasource.entity.User1;
import org.caseor.boot.datasource.mapper.User1Mapper;
import org.caseor.boot.datasource.mapper.UserMapper;
import org.caseor.boot.datasource.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author Fu Kai
 * @since 20211120
 */

@SpringBootTest
public class WithContextTest {

  @Autowired
  RedisService redisService;

  @Autowired
  UserMapper userMapper;

  @Autowired
  User1Mapper user1Mapper;

  @Test
  public void redisTest() {
    redisService.setObject("key2", "value2");
  }

  @Test
  public void mysqlTest() {
    for (User user : userMapper.select(new HashMap<>())) {
      System.out.println(user);
    }
  }

  @Test
  public void oracleTest() {
    for (User1 user : user1Mapper.select(new HashMap<>())) {
      System.out.println(user);
    }
  }

}
