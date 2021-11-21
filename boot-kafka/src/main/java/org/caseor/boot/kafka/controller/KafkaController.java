package org.caseor.boot.kafka.controller;


import org.caseor.common.core.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Fu Kai
 * @since 20210717
 */

@RestController
public class KafkaController {

  @GetMapping
  public Map<String, Object> kafka() {
    return Map.of("data", "hello world", "msg", "success");
  }

  @GetMapping("/success")
  public R<?> success() {
    return R.success();
  }
}
