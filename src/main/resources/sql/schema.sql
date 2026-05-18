

-- 기존 테이블이 있다면 DROP 후 생성하거나 ALTER 사용
DROP TABLE IF EXISTS tb_comment;

CREATE TABLE tb_comment (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    board_id BIGINT NOT NULL,          -- 물리적 FK는 걸지 않지만 관례상 ID는 남겨둡니다.
    content NVARCHAR(1000) NOT NULL,
    writer VARCHAR(50) NOT NULL,
    created_at DATETIME2 DEFAULT GETDATE(),
    is_deleted CHAR(1) DEFAULT 'N'     -- 소프트 삭제 여부 플래그 ('Y' 면 삭제된 데이터)
);

-- 검색 성능을 위해 외래키 제약조건 대신 인덱스를 걸어주는 것이 좋습니다.
CREATE INDEX IX_comment_board_id ON tb_comment(board_id);
