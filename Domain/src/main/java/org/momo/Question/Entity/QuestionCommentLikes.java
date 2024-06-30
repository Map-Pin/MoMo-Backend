package org.momo.Question.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.momo.Base.Entity.Base;
import org.momo.Member.Entity.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "question_comment_likes")
public class QuestionCommentLikes extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private QuestionComment questionComment;
}
