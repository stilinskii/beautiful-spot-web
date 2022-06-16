package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private BoardRepository boardRepository;

    @GetMapping
    public String home(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        model.addAttribute("localdate", LocalDateTime.now());
        return "index";
    }




}
