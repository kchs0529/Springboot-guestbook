package org.zerock.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity{ //상속하면 BaseEntity의 날짜테이블들도 추가되서 총 6개의 컬럼이된다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk로 쓰겠다는 전략
    private Long gno;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(length = 1500,nullable = false)
    private String content;
    @Column(length = 50,nullable = false)
    private String writer;

    public void changeTitle(String title){
        this.title = title;

    }
    public void changeContent(String content){
        this.content = content;
    }
}
