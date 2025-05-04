package org.ratio.agora.profile.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ratio.agora.member.domain.Member;

@Entity
@Table(name = "profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;
    private String profileImageUrl;
    private String bio;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Profile(String nickname, String profileImageUrl, Member member) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.member = member;
    }
}
