package com.example.demo.repository;

import com.example.demo.model.Board;
import com.example.demo.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {
    List<Comments> findAllByBoard(Board board);
    List<Comments> findAllByBoardOrderByIdDesc(Board board);
}
