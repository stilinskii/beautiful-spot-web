package com.example.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@SequenceGenerator(name="MEMBER_SEQ_GENERATOR", sequenceName="MEMBER_NUM_SEQ", initialValue=1, allocationSize=1)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType. SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private int id;
    private String username;
    @Nullable
    private String email;
    private String password;
    @Nullable
    private int enabled;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @ManyToMany
    @JoinTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> board;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @Transient
    private int age;

    public Member(int id, String username, String email, String password, LocalDate dob) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public Object getAge() {
        if(dob==null){
            return null;
        }
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}


