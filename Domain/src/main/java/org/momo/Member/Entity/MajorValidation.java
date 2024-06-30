package org.momo.Member.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.momo.Base.Entity.Base;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "major_validation")
public class MajorValidation extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer memberId;

    @Lob
    private String image;
}
