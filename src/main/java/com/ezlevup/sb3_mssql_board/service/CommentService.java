package com.ezlevup.sb3_mssql_board.service;

import com.ezlevup.sb3_mssql_board.domain.Board;
import com.ezlevup.sb3_mssql_board.domain.Comment;
import com.ezlevup.sb3_mssql_board.repository.BoardRepository;
import com.ezlevup.sb3_mssql_board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long saveComment(Long boardId, String content, String writer) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + boardId));
        
        Comment comment = Comment.createComment(board, content, writer);
        return commentRepository.save(comment).getId();
    }
}