package org.momo.Question.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.momo.Base.Entity.Base;
import org.momo.Department.Department;
import org.momo.Member.Entity.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "question_post")
public class QuestionPost extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Department major;

    @Column(length = 50)
    private String title;

    @Lob
    private String contents;

    @ColumnDefault("0")
    private Integer likes;

    @ColumnDefault("0")
    private Integer comments;

    @ColumnDefault("0")
    private Boolean selection;

    private Long selectedId;

    private Integer rewards;

    @Lob
    private String image;
}
