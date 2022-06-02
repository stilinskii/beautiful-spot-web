package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public String signUpForm(Model model){
        model.addAttribute("member",new Member());
        return "signUp";
    }

    @PostMapping
    public String createAccount(@ModelAttribute Member member){

        memberRepository.save(member);
        return "redirect:/login";
    }

}
