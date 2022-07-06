package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.model.Comments;
import com.example.demo.model.Member;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentsRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Date;

@Slf4j
@Controller
@RequestMapping("/board/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsRepository commentsRepo;
    private final BoardRepository boardRepo;
    private final MemberRepository memberRepo;

    @PostMapping("/add")
    public String add(@ModelAttribute Comments comments, @RequestParam int board_id, Principal principal){

        Board board = boardRepo.findById(board_id).get();
        String username = principal.getName();//로그인한 username
        Member member = memberRepo.findMemberByUsername(username);

        log.info("username={}",username);

        Comments commentsInput = new Comments(new Date(new java.util.Date().getTime()), comments.getContent(), member, board);
        commentsRepo.save(commentsInput);

        return "redirect:/board/article?id="+board_id;
    }

    @GetMapping("/edit/{comment_id}")
    public String edit(@PathVariable int comment_id,int board_id){

        return "redirect:/board/article?id="+board_id;
    }


    @ResponseBody
    @DeleteMapping("/delete/{comment_id}")
    public void delete(@PathVariable int comment_id){
        commentsRepo.deleteById(comment_id);
    }

}
