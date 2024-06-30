package org.momo.Free.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.momo.Base.Entity.Base;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "hot_free_post")
public class HotFreePost extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private FreePost freePost;
}
