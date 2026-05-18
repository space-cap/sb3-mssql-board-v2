package com.ezlevup.sb3_mssql_board.repository;

import com.ezlevup.sb3_mssql_board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 기본 findById, save, findAll, deleteById 자동 제공됨
}