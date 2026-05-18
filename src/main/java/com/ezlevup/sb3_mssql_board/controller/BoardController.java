package com.ezlevup.sb3_mssql_board.controller;

import com.ezlevup.sb3_mssql_board.domain.Board;
import com.ezlevup.sb3_mssql_board.dto.BoardListDto;
import com.ezlevup.sb3_mssql_board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 1. 목록 조회
    @GetMapping("/list")
    public String list(Model model, 
                       @RequestParam(defaultValue = "0") int page) {
        
        // 1. 한 페이지에 10개씩, ID 역순(최신순)으로 정렬 세팅
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        
        // 2. 페이징 처리된 데이터 조회
        Page<BoardListDto> boardPage = boardService.findPaginated(pageable);
        
        // 3. 하단 내비게이션 바 번호 계산 (블록당 5개씩 표시 예시)
        int blockWidth = 5;
        int currentBlock = page / blockWidth;
        int startPage = currentBlock * blockWidth + 1;
        int endPage = Math.min(startPage + blockWidth - 1, boardPage.getTotalPages());
        
        // 4. Thymeleaf로 데이터 전달
        model.addAttribute("boards", boardPage.getContent()); // 현재 페이지 데이터 리스트
        model.addAttribute("page", boardPage);               // Page 정보 객체 전체
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        return "board/list";
    }

    // 2. 상세 조회
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        model.addAttribute("board", boardService.findById(id));
        model.addAttribute("currentPage", page);
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
