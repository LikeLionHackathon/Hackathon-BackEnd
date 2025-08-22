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

    private String username;

    private String email;

    private String password;

    private String profile;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
