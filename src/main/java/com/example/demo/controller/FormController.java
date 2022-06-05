package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/form")
public class FormController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping
    public String form(){
        return "board/form";
    }

    @PostMapping
    public String save(@ModelAttribute Board board,@RequestParam("image") MultipartFile file){
        board.setFilename(file.getOriginalFilename());
        boardRepository.save(board);
        return "redirect:/index";
    }
}
