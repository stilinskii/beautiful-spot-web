package com.example.demo.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Member {

    @Id
    private String id;
    private String nickname;
    private String email;
    private String password;
    private LocalDate dob;
    @Transient
    private int age;

    public Member(String id, String nickname, String email, String password, LocalDate dob) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}


