package com.ezlevup.sb3_mssql_board.repository;

import com.ezlevup.sb3_mssql_board.domain.Board;
import com.ezlevup.sb3_mssql_board.dto.BoardListDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query(
			value = """
					select new com.ezlevup.sb3_mssql_board.dto.BoardListDto(
						b.id,
						b.title,
						b.writer,
						b.createdAt,
						b.viewCnt,
						count(c.id)
					)
					from Board b
					left join b.comments c
					group by b.id, b.title, b.writer, b.createdAt, b.viewCnt
					order by b.id desc
					""",
			countQuery = "select count(b) from Board b"
	)
	Page<BoardListDto> findBoardList(Pageable pageable);
}
