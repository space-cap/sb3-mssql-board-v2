package com.ezlevup.sb3_mssql_board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardListDto {

	private Long id;
	private String title;
	private String writer;
	private LocalDateTime createdAt;
	private int viewCnt;
	private Long commentCount;
}
