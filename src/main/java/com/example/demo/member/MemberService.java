package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    public List<Member> getMember(){
        return memberRepository.findAll();
    }

    //cant test
    public void saveMember(){
        memberRepository.saveAll(List.of(
                new Member(
                        "lhy98410",
                        "jenn",
                        "lhy98410@naver.com",
                        "dfdfd",
                        LocalDate.of(1998, Month.APRIL,10) ,
                        20
                )));
    }


}
