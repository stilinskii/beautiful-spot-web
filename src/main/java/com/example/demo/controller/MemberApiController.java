package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    @PutMapping("/myPage/edit/{username}")
//    public void editInfo(@PathVariable String username, @RequestBody Member member){
//        ResponseEntity entity = null;
//
//        Optional<Member> updateMember = Optional.ofNullable(memberRepository.findMemberByUsername(username));
//
//        updateMember.ifPresent(selectMember->{
//            if(!StringUtils.isEmpty(member.getPassword())){
//                String encodedPassword = passwordEncoder.encode(member.getPassword());
//            selectMember.setPassword(encodedPassword);
//            }
//        memberRepository.save(selectMember);
//        });
//
//    }
}
