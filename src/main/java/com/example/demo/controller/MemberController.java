package com.example.demo.controller;

import com.example.demo.model.MemberLoginForm;
import com.example.demo.service.MemberService;
import com.example.demo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    //로그인후 홈으로 이동
//    @GetMapping(path="/home")
//    public List<Member> home(){
//        return memberService.getMember();
//    }

//   @GetMapping("/signUp")
//   public String signUpForm(){
//        return "signUp";
//   }
    //왜 여기선 꼭 html을 리턴해야하는거지.?
    // @Responsebody 하면 signUp 글자를 띄움

//    @PostMapping("/signUp")
//    public String signUp(MemberSignupForm form){
//        String[] dob = form.getDob().split("-");
//        int bYear = Integer.parseInt(dob[0]);
//        int bMonth = Integer.parseInt(dob[1]);
//        int bDay = Integer.parseInt(dob[2]);
//    Member member = new Member();
//        member.setId(form.getId());
//        member.setPassword(form.getPwd());
//        member.setNickname(form.getNickName());
//        member.setEmail(form.getEmail());
//        member.setDob(LocalDate.of(bYear,bMonth,bDay));
//    memberService.saveMember(member);
//        System.out.println("signup success");
//        return "redirect:/";
//    }

    //   @RequestMapping(value = "/login")
    //    @ResponseBody
    @PostMapping("/login")
    public String login(MemberLoginForm form){
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPwd());
        int memberChk = memberService.chkMember(member);
        //1.아이디비번 다 맞으면 홈으로
        //2. 아이디는 맞는데 비번 틀리면 다시 로그인페이지로
        //3. 아이디 틀리면 회원가입 페이지로.
        //사실 alert띄우고싶은데 어떻게 해야할지 모르겠다.
        if(memberChk==1){
            return "home";
        }else if(memberChk==2){
            return "index";
        }else {
            //<script>alert('fail);
            return "signUp";
        }
    }



//    @PostMapping(value = "/members/new")
//    public String create(MemberForm form) {
//
//        return "redirect:/";
//    }

}
