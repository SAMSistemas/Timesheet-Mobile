package com.samsistemas.timesheet.entity;

/**
 * @author jonatan.salas
 */
public class SessionEntity {

    /**
     *
     */
    private long sessionId;

    /**
     *
     */
    private long userId;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String authCredential;

    /**
     *
     */
    private boolean logged;

    /**
     *
     */
    public SessionEntity() { }

    /**
     *
     * @param sessionId
     * @return
     */
    public SessionEntity setSessionId(long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     *
     * @param userId
     * @return
     */
    public SessionEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    /**
     *
     * @param username
     * @return
     */
    public SessionEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     *
     * @param password
     * @return
     */
    public SessionEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     *
     * @param authCredential
     * @return
     */
    public SessionEntity setAuthCredential(String authCredential) {
        this.authCredential = authCredential;
        return this;
    }

    /**
     *
     * @param logged
     * @return
     */
    public SessionEntity setLogged(boolean logged) {
        this.logged = logged;
        return this;
    }

    /**
     *
     * @return
     */
    public long getSessionId() {
        return sessionId;
    }

    /**
     *
     * @return
     */
    public long getUserId() {
        return userId;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public String getAuthHeader() {
        return authCredential;
    }

    /**
     *
     * @return
     */
    public boolean isLogged() {
        return logged;
    }
}
