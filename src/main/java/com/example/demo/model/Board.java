package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Data
@SequenceGenerator(name="Article_SEQ_GENERATOR", sequenceName="Board_SEQ", initialValue=1, allocationSize=1)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType. SEQUENCE, generator = "Article_SEQ_GENERATOR")
    private Integer id;
    @NotNull
    @Size(min=1, max=30, message = "title can't be empty(less than 30 characters)")
    private String title;

    private String content;

    private String filename;

    private String filepath;
    //작성자도 필요

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
