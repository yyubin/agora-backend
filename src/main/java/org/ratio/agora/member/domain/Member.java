package org.ratio.agora.member.domain;

import jakarta.persistence.*;
import org.ratio.agora.profile.domain.Profile;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;

    private String email;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Profile profile;

    private boolean isPhoneVerified;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    public String getUniqueOauthKey() {
        return provider.name() + ":" + oauthId;
    }
}
