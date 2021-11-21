package org.caseor.boot.helloworld.caffeine.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Fu Kai
 * @since 20210626
 */

@Configuration
public class CaffeineConfig {
  @Bean
  public Cache<String, Object> cache() {
    return Caffeine.newBuilder()
      // 数量
      .maximumSize(1024)
      .expireAfterWrite(30, TimeUnit.MINUTES)
      // 弱引用
      // .weakKeys()
      .weakValues()
      // 删除监听事件
      .removalListener(
        (RemovalListener<String, Object>) (key, val, reason) ->
          System.out.println("key:" + key + ", val:" + val + ", reason:" + reason)
      ).build();
  }
}
