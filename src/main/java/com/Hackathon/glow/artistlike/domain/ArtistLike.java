package com.Hackathon.glow.artistlike.domain;
import com.Hackathon.glow.user.domain.User;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="artistLike")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //좋아요
    private boolean liked;


    @ManyToOne
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="to_user_id")
    private User toUser;



}
