package com.example.demo.member;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
//not working
    @Test
    void test(){
        //memberService.saveMember();
    }

}
