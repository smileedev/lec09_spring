package com.gn.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gn.spring.member.model.vo.Member;

@Controller
public class MemberApiController {
	
	@ResponseBody
	@PostMapping("/join")
	public Map<String, String> createMember(@RequestBody Member vo){
		
		System.out.println(vo);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "회원가입 중 오류가 발생하였습니다.");
		
		return resultMap;
	}
}
