package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {


    @Autowired
    private BoardRepository boardRepository;

    @GetMapping
    public String home(Model model,@PageableDefault(size = 30, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);
        model.addAttribute("boards",boards);
        model.addAttribute("localdate", LocalDateTime.now());
        return "index";
    }




}
