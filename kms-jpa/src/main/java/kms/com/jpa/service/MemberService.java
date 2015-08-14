package kms.com.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kms.com.jpa.domain.Member;
import kms.com.jpa.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	
	
	public Long join(Member member ){
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member){
		List<Member> findmembers = memberRepository.findByName(member.getName());
		if(!findmembers.isEmpty()){
			throw new IllegalStateException("이미존재하는 회원");
		}
	}
	
	
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Member findOne(Member member){
		return memberRepository.findOne(member.getId());
	}
	
	
	
	
}
