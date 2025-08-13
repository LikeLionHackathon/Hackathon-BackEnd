package com.Hackathon.glow.UserDailyExhibition.domain;


import com.Hackathon.glow.User.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="userDaily_exhibition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDailyExhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate recommendationDate;


    @ManyToOne
    @JoinColumn(name="userId")
    private User user;





}
