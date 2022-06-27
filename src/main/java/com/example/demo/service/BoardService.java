package com.example.demo.service;

import com.example.demo.model.Board;
import com.example.demo.model.Member;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    //board의 필드인 member의 username을 set한 후 board에 set 후 데이터에 저장
    public Board save(Board board, String username){
        Member member = memberRepository.findMemberByUsername(username);
        board.setMember(member);

        return boardRepository.save(board);
    }

    public Board findById(Integer id){
        return boardRepository.findById(id).orElse(null);
    }

    public void deleteArticle(Integer id){
        boardRepository.deleteById(id);
    }



}
