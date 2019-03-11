package com.matchbook.sdk.core.clients.rest.dtos.user;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class Logout implements MatchbookResponse {

    private String sessionToken;
    private Long userId;
    private String username;

    public String getSessionToken() {
        return sessionToken;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return Logout.class.getSimpleName() + " {" +
                "sessionToken=" + sessionToken +
                ", userId=" + userId +
                ", username=" + username +
                "}";
    }
}