package org.momo.Free.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.momo.Base.Entity.BaseEntity;
import org.momo.Member.Entity.MemberEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "free_post")
public class FreePostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(length = 50)
    private String title;

    @Lob
    private String contents;

    @ColumnDefault("0")
    private Integer likes;

    @ColumnDefault("0")
    private Integer comments;
}
