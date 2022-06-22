package com.example.demo.repository;

import com.example.demo.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    Page<Board> findAll(Pageable pageable);
}
