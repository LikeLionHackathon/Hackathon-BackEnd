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

    private String profileImageUrl = "https://mutsa-s3-prac.s3.us-east-1.amazonaws.com/mainProfile.svg";

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
