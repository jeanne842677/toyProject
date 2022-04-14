package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;			//의존성 주입

	public void insertMember(Member member) {			//리포지토리에 파라미터 전달
		
		memberRepository.insertMember(member);		
		
	}

	public Member authenticationUser(Member member) {
		
		return memberRepository.authenticationUser(member);
	}
	
	

}

