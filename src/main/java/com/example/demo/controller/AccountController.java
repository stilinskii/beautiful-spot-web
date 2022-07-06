package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.example.demo.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @GetMapping("/login")
    public String loginForm(){
        return "account/login";
    }

    @GetMapping("/signUp")
    public String signUpForm(@ModelAttribute Member member){

        return "account/signUp";
    }

    @PostMapping("/signUp")
    public String createAccount(@Valid @ModelAttribute Member member, BindingResult bindingResult){
//        memberValidator.validate(member, bindingResult);
//        if(bindingResult.hasErrors()){
//            return "account/signUp";
//        }
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
