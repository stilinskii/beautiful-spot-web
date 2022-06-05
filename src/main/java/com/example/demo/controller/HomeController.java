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

@Controller
public class HomeController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/signUp")
    public String signUpForm(Model model){
        model.addAttribute("member",new Member());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String createAccount(@ModelAttribute Member member){
        memberRepository.save(member);
        return "redirect:/login";
    }


}
