package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

   @GetMapping("/signUp")
   public String signUpForm(){
        return "signUp";
   }

    @PostMapping("/signUp")
    public String signUp(){
        return "redirect:/";
        //왜 여기선 꼭 html을 리턴해야하는거지.? 그냥 스트링 뿌리는건안되나
        // @RequestBody도 안됨!
    }

    @PostMapping("/home")
    public String login(MemberLoginForm form){
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPwd());
        return "home";
    }



//    @PostMapping(value = "/members/new")
//    public String create(MemberForm form) {
//
//        return "redirect:/";
//    }

}
