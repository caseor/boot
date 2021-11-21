package org.caseor.boot.helloworld.knife4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fu Kai
 * @since 20210604
 */

@RequestMapping("/knife4j")
public class Knife4jController {

  @GetMapping
  public String hello() {
    return "hello world";
  }
}
