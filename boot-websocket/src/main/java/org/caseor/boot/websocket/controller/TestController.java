package org.caseor.boot.websocket.controller;

import org.caseor.boot.websocket.service.WebsocketService;
import org.caseor.common.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu Kai
 * @since 20210121
 */

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private WebsocketService websocketService;

  @PostMapping
  public R<?> push() {
    websocketService.groupSending("hhhhh");
    return R.success(null, "推送成功!");
  }
}
