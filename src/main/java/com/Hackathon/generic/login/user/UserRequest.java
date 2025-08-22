package com.Hackathon.generic.login.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//이 기능이 실행되려면 어떤 기능이 필요한가
//유저를 찾아야지?
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//request에서 앞으로 생성/ 조회 요청 다 생각하기 !
public class UserRequest {
    //생성과 조회 둘다 해야하니까 !
    private String username;
    private String password;
    private String email;

    public User toEntity()
    {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .build();
    }
}
