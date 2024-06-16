package org.momo.Question.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.momo.Base.Entity.BaseEntity;
import org.momo.Member.Entity.MemberEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "question_comment_likes")
public class QuestionCommentLikesEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private QuestionCommentEntity questionComment;
}
