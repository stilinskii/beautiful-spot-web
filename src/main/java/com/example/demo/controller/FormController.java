package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

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
}
