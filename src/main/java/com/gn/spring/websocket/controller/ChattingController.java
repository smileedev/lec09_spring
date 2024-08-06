package com.gn.spring.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChattingController {
	
	@GetMapping("/chattingPage")
	public String chattingPage() {
		return "chat/list";
	}
}
