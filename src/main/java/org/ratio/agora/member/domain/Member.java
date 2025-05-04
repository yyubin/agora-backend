package org.ratio.agora.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ratio.agora.profile.domain.Profile;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;

    private String email;

    @Setter
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Profile profile;

    private boolean isPhoneVerified;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    public String getUniqueOauthKey() {
        return provider.name() + ":" + oauthId;
    }

    @Builder
    public Member(String oauthId, OAuthProvider provider, String email, Profile profile, boolean isPhoneVerified, String phoneNumber) {
        this.oauthId = oauthId;
        this.provider = provider;
        this.email = email;
        this.profile = profile;
        this.isPhoneVerified = isPhoneVerified;
    }

}
