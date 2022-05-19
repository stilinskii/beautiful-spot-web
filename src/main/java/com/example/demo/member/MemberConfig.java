package com.example.demo.member;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class MemberConfig {

    @Bean
CommandLineRunner commandLineRunner(MemberRepository repository){
    return args -> {
       repository.saveAll(List.of(
                new Member(
                        "lhy98410",
                        "jenn",
                        "lhy98410@naver.com",
                        "dfdfd",
                        LocalDate.of(1998, Month.APRIL,10) ,
                        20
                )));
    };
}
}
