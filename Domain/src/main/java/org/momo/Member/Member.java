package org.momo.Member;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
}
