package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //로그인후 홈으로 이동
//    @GetMapping(path="/home")
//    public List<Member> home(){
//        return memberService.getMember();
//    }

    @PostMapping(value = "/home")
    public List<Member> login() {

        return memberService.getMember();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

//    @PostMapping(value = "/members/new")
//    public String create(MemberForm form) {
//
//        return "redirect:/";
//    }

}
