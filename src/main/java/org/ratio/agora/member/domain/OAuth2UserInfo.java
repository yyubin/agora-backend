package org.ratio.agora.member.domain;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getOauthId();
    public abstract String getEmail();
    public abstract String getName();
    public abstract String getPicture();
}
