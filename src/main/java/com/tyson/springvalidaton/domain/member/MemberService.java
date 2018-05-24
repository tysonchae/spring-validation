package com.tyson.springvalidaton.domain.member;

import com.tyson.springvalidaton.domain.exception.ValidCustomException;
import com.tyson.springvalidaton.domain.member.dto.MemberRequestDto;

import com.tyson.springvalidaton.domain.member.dto.MemberResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long save(MemberRequestDto memberRequestDto){
        verifyDuplicateEmail(memberRequestDto.getEmail());
        return memberRepository.save(memberRequestDto.toEntity()).getId();
    }

    private void verifyDuplicateEmail(String email){
        if(memberRepository.findByEmail(email).isPresent()){
            throw new ValidCustomException("이미 사용중인 이메일 주소입니다.", "email");
        }
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll(){
        return memberRepository
                .findAll()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }
}
