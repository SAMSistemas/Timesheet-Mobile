package com.samsistemas.timesheet.commons.model;

/**
 * Entity that represents a Session in a SharedPreferences file
 *
 * @author jonatan.salas
 */
public class SessionEntity {

    /**
     * The id of the session
     */
    private long sessionId;

    /**
     * The id of the user at this session
     */
    private long userId;

    /**
     * The username
     */
    private String username;

    /**
     * The password
     */
    private String password;

    /**
     * The username and password encrypted as base64
     */
    private String authCredential;

    /**
     * A boolean flag indicating the user status of login
     */
    private boolean logged;

    /**
     * Public constructor
     */
    public SessionEntity() { }

    /**
     * Setter as builder pattern
     *
     * @param sessionId the id of the user session
     * @return a SessionEntity object
     */
    public SessionEntity setSessionId(long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param userId the userId as long representation
     * @return a SessionEntity object
     */
    public SessionEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param username the username as String representation
     * @return a SessionEntity object
     */
    public SessionEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param password the password as String representation
     * @return a SessionEntity object
     */
    public SessionEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param authCredential the username and password encrypted as base64
     * @return a SessionEntity object
     */
    public SessionEntity setAuthCredential(String authCredential) {
        this.authCredential = authCredential;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param logged the status of the user
     * @return a SessionEntity object
     */
    public SessionEntity setLogged(boolean logged) {
        this.logged = logged;
        return this;
    }

    /**
     *
     * Getter for SessionId
     *
     * @return the value of sessionId field
     */
    public long getSessionId() {
        return sessionId;
    }

    /**
     * Getter for UserId
     *
     * @return the value of userId field
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Getter for Username
     *
     * @return the value of username field
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for Password
     *
     * @return the value of password field
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for AuthHeader
     *
     * @return the value of authCredential field
     */
    public String getAuthHeader() {
        return authCredential;
    }

    /**
     * Getter for Logged
     *
     * @return the value of logged field
     */
    public boolean isLogged() {
        return logged;
    }
}
