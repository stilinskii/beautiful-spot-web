package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/login")
    public String loginForm(){
        return "account/login";
    }

    @GetMapping("/signUp")
    public String signUpForm(){
        return "account/signUp";
    }

    @PostMapping("/signUp")
    public String createAccount(@ModelAttribute Member member){
        memberService.save(member);
        return "redirect:/account/login";
    }

    @GetMapping("/myPage")
    public String myPage(){
        return "account/myPage";
    }
}
