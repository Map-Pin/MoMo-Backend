package org.momo.Question.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.momo.Base.Entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "questionpost")
public class QuestionPostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long majorId;

    @Column(length = 50)
    private String title;

    @Lob
    private String contents;
    private Integer likes;
    private Integer comments;
    private Boolean selection;
    private Long selectedId;
    private Integer rewards;
    @Lob
    private String image;
}
