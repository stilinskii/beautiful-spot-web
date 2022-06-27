package com.example.demo.service;

import com.example.demo.model.Member;
import com.example.demo.model.Role;
import com.example.demo.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public List<Member> getMember(){
        return memberRepository.findAll();
    }

    //cant test
    public Member save(Member member){
        log.info("member={}",member.getUsername());
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        member.setEnabled(1);//1=true
        Role role = new Role();
        role.setId(1);
        member.getRoles().add(role);
        return memberRepository.save(member);
    }

    public Member findByUsername(String username){
        Member member = memberRepository.findMemberByUsername(username);

        return memberRepository.findMemberByUsername(username);
    }

    public void editMemberInfo(Member member){
        Member originalMember = memberRepository.findById(member.getId()).get();

        if(!StringUtils.isEmpty(member.getPassword())){
            String encodedPassword = passwordEncoder.encode(member.getPassword());
            member.setPassword(encodedPassword);
        }else {
            member.setPassword(originalMember.getPassword());
        }
        memberRepository.save(member);
    }




}
