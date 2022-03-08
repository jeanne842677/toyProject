package com.kh.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;

@Controller
@RequestMapping("member")

public class MemberController {
	
	@Autowired									//의존성 주입
	private MemberService memberService;		
	
	@GetMapping("join-form")					//join-form과 페이지 연결
	public void joinForm() {}					
	
	@PostMapping("join")						//join-form과 페이지 연결 model째로 parameter 받아오기
	public String join(Member member) {
		memberService.insertMember(member);
		return "index";
	}
	

}
