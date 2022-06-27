package com.example.demo.controller;

import com.example.demo.file.FileStore;
import com.example.demo.model.Board;
import com.example.demo.model.Member;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;
import com.example.demo.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {


    private final BoardRepository boardRepository;

    private final BoardService boardService;
    private final BoardValidator boardValidator;
    private final FileStore fileStore;
    @GetMapping
    public String form(Model model,@PageableDefault(size = 3, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
//        Page<Board> boards = boardRepository.findAll(pageable)
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
    public String formSubmit(@Valid Board board, BindingResult bindingResult, @RequestPart(value = "image",required=false) MultipartFile image, RedirectAttributes redirectAttributes, Authentication authentication) throws IOException{
       boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
           return "board/form";
       }
        log.info(board.getTitle());
        board.setFilename(fileStore.saveImage(image));

        boardService.save(board,authentication.getName());

        redirectAttributes.addAttribute("id",board.getId());

        return "redirect:/board/article";

    }

    private Board setImageToBoard(Board board, MultipartFile image) throws IOException {
        String imageFileName = UUID.randomUUID()+"_"+ image.getOriginalFilename();
        String path = System.getProperty("user.dir")+"/src/main/resources/static/images/";
        Path imagePath = Paths.get(path + imageFileName);
        Files.write(imagePath, image.getBytes());

        board.setFilename(imageFileName);
        board.setFilepath(imageFileName);

        return board;
    }


    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Integer id){
        if(id == null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardService.findById(id);
            model.addAttribute("board",board);
        }
        return "board/form";
    }

    @GetMapping("/article")
    public String article(Model model, @RequestParam(required = false) Integer id){
        if(id == null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardService.findById(id);
            model.addAttribute("board",board);
        }
        return "board/article";
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable String fileName, HttpServletResponse response) throws MalformedURLException {

        return new UrlResource("file:"+fileStore.getFullPath(fileName));
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Integer id){
        boardService.deleteArticle(id);
//        return "redirect:/board";
    }





}
