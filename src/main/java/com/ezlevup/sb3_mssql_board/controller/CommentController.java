package com.ezlevup.sb3_mssql_board.controller;

import com.ezlevup.sb3_mssql_board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록 처리
    @PostMapping("/write/{boardId}")
    public String writeComment(@PathVariable Long boardId,
                               @RequestParam String writer,
                               @RequestParam String content) {
        
        commentService.saveComment(boardId, content, writer);
        
        // 댓글 작성이 완료되면 다시 해당 게시글 상세보기 페이지로 리다이렉트
        return "redirect:/board/view/" + boardId;
    }
}