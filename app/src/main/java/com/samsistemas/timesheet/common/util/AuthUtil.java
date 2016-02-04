package com.samsistemas.timesheet.common.util;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that provides helper methods when working with OAuth.
 *
 * @author jonatan.salas
 */
public final class AuthUtil {
    private static final String LOG_TAG = AuthUtil.class.getSimpleName();

    /**
     * Charset encode
     */
    private static final String CHARSET = "UTF-8";

    /**
     * Pattern for String.
     */
    private static final String STRING_PATTERN = "%s:%s";

    /**
     * Key used to identify, auth data in the header hash map.
     */
    private static final String KEY = "Authorization";

    private AuthUtil() { }

    /**
     * Method that generates the authentication headers.
     *
     * @param credentials username and password, as String array.
     * @return a Map containing the data needed for auth.
     */
    public static Map<String, String> getAuthHeaders(@NonNull final String[] credentials) {
        final Map<String, String> requestParams = new HashMap<>(1);
        final String username = credentials[0];
        final String password = credentials[1];

        requestParams.put(KEY, getAuthCredential(username, password));
        return requestParams;
    }

    /**
     * Method that gets the string encoded as Base64.
     *
     * @param username a string containing the username, not null.
     * @param password a string containing the password, not null.
     * @return a String with the username and password encoded as Base64.
     */
    public static String getAuthCredential(@NonNull final String username,  @NonNull final String password) {
        final String credentials = String.format(STRING_PATTERN, username, password);
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        return auth;
    }
}
