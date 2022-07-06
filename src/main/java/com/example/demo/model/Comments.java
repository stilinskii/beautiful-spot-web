package com.example.demo.model;

import lombok.*;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
@SequenceGenerator(name="Comments_SEQ_GENERATOR", sequenceName="Comments_SEQ", initialValue=1, allocationSize=1)
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType. SEQUENCE, generator = "Comments_SEQ_GENERATOR")
    private int id;
    private Date written_date;
    private String content;

    public Comments(Date written_date, String content, Member member, Board board) {
        this.written_date = written_date;
        this.content = content;
        this.member = member;
        this.board = board;
    }

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
