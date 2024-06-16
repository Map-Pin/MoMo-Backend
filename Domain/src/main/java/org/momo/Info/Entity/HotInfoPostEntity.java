package org.momo.Info.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.momo.Base.Entity.BaseEntity;
import org.momo.Department.MajorEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "hot_info_post")
public class HotInfoPostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private MajorEntity major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private InfoPostEntity infoPost;
}
