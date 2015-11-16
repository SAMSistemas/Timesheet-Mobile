package com.samsistemas.timesheet.entity;

/**
 * @author jonatan.salas
 */
public class SessionEntity {
    private long sessionId;
    private long userId;
    private String username;
    private String password;
    private String authCredential;
    private boolean logged;

    public SessionEntity() {}

    public SessionEntity setSessionId(long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public SessionEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public SessionEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public SessionEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public SessionEntity setAuthCredential(String authCredential) {
        this.authCredential = authCredential;
        return this;
    }

    public SessionEntity setLogged(boolean logged) {
        this.logged = logged;
        return this;
    }

    public long getSessionId() {
        return sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthHeader() {
        return authCredential;
    }

    public boolean isLogged() {
        return logged;
    }
}
