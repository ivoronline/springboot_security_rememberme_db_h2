package com.ivoronline.springboot_security_rememberme_db_h2.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

  @ResponseBody
  @RequestMapping("/SayHello")
  public String sayHello() {
    return "Hello from Controller";
  }
}
