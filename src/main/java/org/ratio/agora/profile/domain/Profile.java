package org.ratio.agora.profile.domain;

import jakarta.persistence.*;
import org.ratio.agora.member.domain.Member;

@Entity
@Table(name = "profile")
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
}
