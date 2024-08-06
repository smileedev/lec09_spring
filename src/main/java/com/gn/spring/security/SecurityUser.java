package com.gn.spring.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.gn.spring.member.model.vo.Member;

import lombok.Getter;

@Getter
public class SecurityUser extends User{
	
	
	private static final long serialVersionUID = 1L;
	
	private Member member;
	
	// 생성자
	public SecurityUser(Member member) {
		super(member.getUser_id(), member.getUser_pw(), 
				AuthorityUtils.createAuthorityList(member.getUser_id().equals("admin")?"ROLE_ADMIN":"ROLE_USER"));
		this.member = member;
		
	}
}
