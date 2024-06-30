package org.momo.Member.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.momo.Base.Entity.Base;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "email_validation")
public class EmailValidation extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 6)
    private String code;

    @Column(length = 15)
    private String email;
}
