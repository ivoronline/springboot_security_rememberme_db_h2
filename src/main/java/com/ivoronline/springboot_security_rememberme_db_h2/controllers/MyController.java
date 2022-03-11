package com.ivoronline.springboot_security_rememberme_db_h2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  //=================================================================
  // HELLO
  //=================================================================
  @RequestMapping("Hello")
  public String hello() {
    return "Hello from Controller";
  }

}
