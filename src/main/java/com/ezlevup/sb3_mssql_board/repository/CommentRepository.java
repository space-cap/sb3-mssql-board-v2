package com.ezlevup.sb3_mssql_board.repository;

import com.ezlevup.sb3_mssql_board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}