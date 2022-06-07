package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class FormController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping
    public String form(){
        return "board/form";
    }

    @PostMapping("/form")
    public String save(@ModelAttribute Board board,@RequestParam("image") MultipartFile image) throws IOException{
       String imageFileName = UUID.randomUUID()+"_"+image.getOriginalFilename();
       String path = System.getProperty("user.dir")+"/src/main/resources/static/images/";
        Path imagePath = Paths.get(path + imageFileName);
        Files.write(imagePath, image.getBytes());


        board.setFilename(imageFileName);
        board.setFilepath("/images/"+imageFileName);

        boardRepository.save(board);


        return "redirect:/";
    }

    @GetMapping("/form")
    public String formSubmit(Model model, @RequestParam(required = false) Integer id){
        if(id == null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }
        return "board/form";
    }

    @GetMapping("/article")
    public String article(Model model, @RequestParam(required = false) Integer id){
        if(id == null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }
        return "board/article";
    }


}
