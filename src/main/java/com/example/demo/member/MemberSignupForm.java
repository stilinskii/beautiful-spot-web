package com.example.demo.member;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MemberSignupForm {
    private String nickName;
    private String id;
    private String pwd;
    private String email;
    private String dob;
}
