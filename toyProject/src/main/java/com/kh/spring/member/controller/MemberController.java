package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.util.CookieGenerator;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;

@Controller
@RequestMapping("member")

public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired									//의존성 주입
	private MemberService memberService;		
	
	@GetMapping("join-form")					//join-form과 페이지 연결
	public void joinForm() {}					
	
	@PostMapping("join")						//join-form과 페이지 연결 model째로 parameter 받아오기
	public String join(Member member) {
		memberService.insertMember(member);
		return "index";
	}
	
	@PostMapping("join-json")
	public String joinWithJson(@RequestBody Member member) {
		logger.debug(member.toString());
		return "index";
	}
	
	//로그인 페이지 이동 메서드 
	//메서드명 : login
	@GetMapping("login")
	public void login() {}
	
	//로그인 실행 메서드
	//메서드명 : loginlmpl
	//재지정할 jsp : mypage
	@PostMapping("login")
	public String loginlmpl(Member member, HttpSession session) {
		Member certifiedUser = memberService.authenticationUser(member);
		session.setAttribute("authentication", certifiedUser);
//		logger.debug(certifiedUser.toString());
		return "redirect:/member/mypage";
	}
	
	@GetMapping("mypage")
	public void mypage(@CookieValue(name="JSESSIONID") String sessionId,
						@SessionAttribute(name="authentication") Member member
						,HttpServletResponse response) {
		
		//Cookie 생성 및 응답헤더에 추가
		CookieGenerator cookieGenerator = new CookieGenerator();
		cookieGenerator.setCookieName("testCookie");
		cookieGenerator.addCookie(response,"test cookie");
		
		logger.debug("@CookieValue : " + sessionId);
		logger.debug("@SessionId : " + member);
	}
	

}
