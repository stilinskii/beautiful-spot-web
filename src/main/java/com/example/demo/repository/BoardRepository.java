package com.example.demo.repository;

import com.example.demo.model.Board;
import com.example.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {
}
