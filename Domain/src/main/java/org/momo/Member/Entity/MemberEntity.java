package org.momo.Member.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.momo.Base.Entity.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
public class MemberEntity extends BaseEntity {
    @Id
    private Long memberId;

    @Column(nullable = false)
    private Long major;
    @Column(length = 15)
    private String name;

    @Column(length = 25)
    private String nickname;

    @Column(length = 50)
    private String email;

    @Column(length = 15)
    private String password;

    private LocalDate birth;

    @Column(length = 13)
    private String phone;

    @Column(length = 45)
    private String college;

    private Boolean emailVerified;
    private Boolean majorVerified;
    @Lob
    private Long profileImage;
    private Integer grade;
}
