package kr.co.boardservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table(name = "tbl_board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "identity")
    private String identity;

    @Builder
    public BoardEntity(String title, String content, String identity) {
        this.title = title;
        this.content = content;
        this.identity = identity;
    }
}
