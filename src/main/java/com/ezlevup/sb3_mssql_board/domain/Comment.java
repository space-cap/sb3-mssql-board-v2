package com.ezlevup.sb3_mssql_board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE tb_comment SET is_deleted = 'Y' WHERE id = ?")
@SQLRestriction("is_deleted = 'N'")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 물리적 FK는 없지만 객체 지향적 연관관계는 유지합니다.
    // JPA가 내부적으로 조인할 때 FK 제약조건 유무는 상관없습니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Board board;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "is_deleted", nullable = false)
    private String isDeleted = "N";

    public static Comment createComment(Board board, String content, String writer) {
        Comment comment = new Comment();
        comment.board = board;
        comment.content = content;
        comment.writer = writer;
        return comment;
    }
}
