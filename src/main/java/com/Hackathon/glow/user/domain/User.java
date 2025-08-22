package com.Hackathon.glow.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String loginId;

    private String password;

    private String nickname;

    private String email;

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
