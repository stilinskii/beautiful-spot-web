package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Slf4j
@Controller
@RequestMapping("/board")
public class FormController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;
    @GetMapping
    public String form(Model model,@PageableDefault(size = 1) Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);
        int[] pageNum = pageNum(boards);
//        int startPage = boards.getPageable().getPageNumber()>=boards.getTotalPages()-3 ? boards.getTotalPages()-4:Math.max(1,boards.getPageable().getPageNumber()-1);
//        int endPage = boards.getPageable().getPageNumber()>=boards.getTotalPages()-3 ? boards.getTotalPages():Math.min(startPage+4,boards.getTotalPages());
        model.addAttribute("startPage",pageNum[0]);
        model.addAttribute("endPage",pageNum[1]);
        model.addAttribute("boards",boards);
        return "board/board";
    }
    private int[] pageNum(Page<Board> boards) {
        int startPage,endPage;
        if(boards.getTotalPages()<=5){
            startPage= 1;
            endPage= boards.getTotalPages();
        }else if(boards.getPageable().getPageNumber()>= boards.getTotalPages()-3 ){
            startPage= boards.getTotalPages()-4;
            endPage= boards.getTotalPages();
        }else{
            startPage= Math.max(1, boards.getPageable().getPageNumber()-1);
            endPage= Math.min(startPage+4, boards.getTotalPages());
        }

//        int startPage = boards.getPageable().getPageNumber()>=boards.getTotalPages()-3 ? boards.getTotalPages()-4:Math.max(1,boards.getPageable().getPageNumber()-1);
//        int endPage = boards.getPageable().getPageNumber()>=boards.getTotalPages()-3 ? boards.getTotalPages():Math.min(startPage+4,boards.getTotalPages());
        return  new int[]  {startPage, endPage};
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board,BindingResult bindingResult, @RequestParam(value = "image",required=false) MultipartFile image) throws IOException{
       boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
           log.info("bindingResult={}",bindingResult.getObjectName());
           return "board/form";
       }


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
    public String form(Model model, @RequestParam(required = false) Integer id){
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
