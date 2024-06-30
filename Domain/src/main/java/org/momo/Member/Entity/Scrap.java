package org.momo.Member.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.momo.Base.Entity.Base;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "scrap")
public class Scrap extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 25)
    private String type;

    private Long subjectId;
}
