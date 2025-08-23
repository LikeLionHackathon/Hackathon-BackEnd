package com.Hackathon.glow.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="user",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")})
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

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
