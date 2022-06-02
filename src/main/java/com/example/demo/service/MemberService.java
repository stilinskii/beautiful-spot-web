package com.example.demo.service;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;


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
