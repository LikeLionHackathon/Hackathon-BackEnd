package com.Hackathon.glow.spaceuser.domain;

import com.Hackathon.glow.exhibitionspace.domain.ExhibitionSpace;
import com.Hackathon.glow.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="spaceuser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SpaceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //공간 사용하는 작가
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    //공실
    @ManyToOne
    @JoinColumn(name = "exhibitionspaceId")
    private ExhibitionSpace exhibitionSpace;

}
