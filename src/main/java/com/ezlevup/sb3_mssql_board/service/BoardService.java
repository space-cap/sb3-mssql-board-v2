package com.ezlevup.sb3_mssql_board.service;

import com.ezlevup.sb3_mssql_board.domain.Board;
import com.ezlevup.sb3_mssql_board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    // 글 목록 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 글 상세 조회
    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }

    // 글 저장
    @Transactional
    public Long save(Board board) {
        return boardRepository.save(board).getId();
    }

    // 글 삭제
    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}