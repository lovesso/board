package com.kh.board.domain.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/view")
public class ApiViewController {

  @GetMapping("/comments")
  public String comments(){

    return "api/view/comments";
  }
}
