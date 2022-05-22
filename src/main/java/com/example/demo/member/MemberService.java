package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

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
    public void saveMember(Member member){
        //아이디 이메일 중복체크 로직 추가
        memberRepository.save(member);
    }

    public int chkMember(Member member){
        Optional<Member> byId = memberRepository.findById(member.getId());
        if(byId.isPresent()){
            if(byId.get().getPassword().equals(member.getPassword())){
                return 1;//login success
            }else{
                return 2;//wrong password
            }
        }else{
            return 3;//user not found
        }
    }


}
