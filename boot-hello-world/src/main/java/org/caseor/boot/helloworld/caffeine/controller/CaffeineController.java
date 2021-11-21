package org.caseor.boot.helloworld.caffeine.controller;

import com.github.benmanes.caffeine.cache.Cache;
import org.caseor.common.core.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Fu Kai
 * @since 20210626
 */

@RestController
public class CaffeineController {

  private final Cache<String, Object> cache;

  @Autowired
  public CaffeineController(Cache<String, Object> cache) {
    this.cache = cache;
  }

  @PostMapping("/add")
  public R<?> add(String key, String val) {
    cache.put(key, val);
    return R.success("add success");
  }

  @DeleteMapping
  public R<?> delete(String key) {
    cache.invalidate(key);
    return R.success();
  }

  @GetMapping
  public R<?> get(String key) {
    return R.success((String) cache.getIfPresent(key));
  }

  @GetMapping("/list")
  public R<?> list() {
    ConcurrentMap<String, Object> chm = cache.asMap();
    for (Map.Entry<String, Object> entry : chm.entrySet()) {
      System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }
    return R.success();
  }

}
