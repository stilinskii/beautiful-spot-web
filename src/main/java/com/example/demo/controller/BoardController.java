package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;
    @GetMapping
    public String form(Model model,@PageableDefault(size = 3, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int[] pageNum = pageNum(boards);
        model.addAttribute("startPage",pageNum[0]);
        model.addAttribute("endPage",pageNum[1]);
        model.addAttribute("boards",boards);
        return "board/board";
    }
    private int[] pageNum(Page<Board> boards) {
        int startPage,endPage;
        int nowPage=boards.getPageable().getPageNumber();
        int totalPage=boards.getTotalPages();
        if(totalPage<=5){
            startPage= 1;
            endPage= totalPage;
        }else if(nowPage>= totalPage-3 ){
            startPage= totalPage-4;
            endPage= totalPage;
        }else {
            startPage = Math.max(1, nowPage - 1);
            endPage = Math.min(startPage + 4, totalPage);
        }
        return  new int[]  {startPage, endPage};
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board, BindingResult bindingResult, @RequestPart(value = "image",required=false) MultipartFile image, RedirectAttributes redirectAttributes) throws IOException{
       boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
           return "board/form";
       }
        log.info(board.getTitle());
        log.info("img={}",image.getOriginalFilename());

        boardRepository.save(setImageToBoard(board, image));

        redirectAttributes.addAttribute("id",board.getId());

        return "redirect:/board/article";

    }

    private Board setImageToBoard(Board board, MultipartFile image) throws IOException {
        String imageFileName = UUID.randomUUID()+"_"+ image.getOriginalFilename();
        String path = System.getProperty("user.dir")+"/src/main/resources/static/images/";
        Path imagePath = Paths.get(path + imageFileName);
        Files.write(imagePath, image.getBytes());

        board.setFilename(imageFileName);
        board.setFilepath("/images/"+imageFileName);

        return board;
    }
//    private Board setImageToBoard(Board board, MultipartFile image, HttpSession session) throws IOException {
//        String imageFileName = UUID.randomUUID()+"_"+ image.getOriginalFilename();
//        String saveDirectory = session.getServletContext().getRealPath("/");
//        String savePath= saveDirectory +"images" + File.separator;
//        File imageDir= new File(saveDirectory);
//        if(!imageDir.exists()) {
//            imageDir.mkdir();
//        }
////       String path = System.getProperty("user.dir")+"/src/main/resources/static/images/";
//        Path imagePath = Paths.get(savePath + imageFileName);
//        Files.write(imagePath, image.getBytes());
//
//        board.setFilename(imageFileName);
//        board.setFilepath("/images/"+imageFileName);
//
//        return board;
//    }

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
