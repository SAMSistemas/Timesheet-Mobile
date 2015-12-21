package com.samsistemas.timesheet.util;

/**
 * Utility class that provides key associated to SharedPreferences.
 * 
 * @author jonatan.salas
 */
public final class SharedPreferenceKeys {

    /**
     * Key used to retrieve/save session id from a SharedPreferences file
     */
    public static final String SESSION_ID = "session_id";

    /**
     * Key used to retrieve/save user id from a SharedPreferences file
     */
    public static final String USER_ID = "user_id";

    /**
     * Key used to retrieve/save username from a SharedPreferences file
     */
    public static final String USERNAME = "username";

    /**
     * Key used to retrieve/save password from a SharedPreferences file
     */
    public static final String PASSWORD = "password";

    /**
     * Key used to retrieve/save auth header from a SharedPreferences file
     */
    public static final String AUTH_HEADER = "auth_header";

    /**
     * Key used to retrieve/save logged status from a SharedPreferences file
     */
    public static final String LOGGED_IN = "logged_in";

    /**
     * Key used to retrieve/save preferences from a SharedPreferences file
     */
    public static final String FILENAME = "preferences";

    private SharedPreferenceKeys() { }
}
