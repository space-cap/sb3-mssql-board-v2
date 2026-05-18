package com.ezlevup.sb3_mssql_board.controller;

import com.ezlevup.sb3_mssql_board.domain.Board;
import com.ezlevup.sb3_mssql_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 1. 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "board/list";
    }

    // 2. 상세 조회
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "board/view";
    }

    // 3. 글 작성 폼 이동
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/write";
    }

    // 4. 글 저장 처리
    @PostMapping("/write")
    public String write(@ModelAttribute Board board) {
        boardService.save(board);
        return "redirect:/board/list";
    }
}