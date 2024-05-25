package org.momo.Question.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.momo.Base.Entity.BaseEntity;
import org.momo.Department.MajorEntity;
import org.momo.Member.Entity.MemberEntity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "question_post")
public class QuestionPostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private MajorEntity major;

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
