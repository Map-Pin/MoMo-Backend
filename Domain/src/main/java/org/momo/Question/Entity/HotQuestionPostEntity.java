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
@Table(name = "hotquestionpost")
public class HotQuestionPostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotQuestionId;

    @Column(nullable = false)
    private Long majorId;

    @Column(nullable = false)
    private Long postId;
}
