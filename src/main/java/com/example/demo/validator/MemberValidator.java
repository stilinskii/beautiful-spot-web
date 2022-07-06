package com.example.demo.validator;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member m = (Member) target;
        if(StringUtils.isEmpty(m.getUsername())){
            errors.rejectValue("username","key","enter username");
        }else {
            if(memberRepository.findMemberByUsername(m.getUsername())!=null)
                errors.rejectValue("username","key","username duplicate. Please enter other username");
        }
    }
}
