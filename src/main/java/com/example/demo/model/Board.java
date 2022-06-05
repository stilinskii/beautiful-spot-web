package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@SequenceGenerator(name="Article_SEQ_GENERATOR", sequenceName="Board_SEQ", initialValue=1, allocationSize=1)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType. SEQUENCE, generator = "Article_SEQ_GENERATOR")
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;
    //작성자도 필요
}