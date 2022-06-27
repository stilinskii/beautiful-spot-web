package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/myPage/{username}")
    public String myPage(@PathVariable String username, Model model){
        model.addAttribute("member",memberService.findByUsername(username));
        return "account/myPage";
    }


    @PostMapping("/myPage/edit/{username}")
    public String editInfo(@PathVariable String username, @ModelAttribute Member member, RedirectAttributes redirectAttributes){

        memberService.editMemberInfo(member);
        redirectAttributes.addAttribute("username",username);
        return "redirect:/account/myPage/{username}";
    }



}
