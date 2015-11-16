package com.samsistemas.timesheet.util;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jonatan.salas
 */
public class AuthUtil {
    private static final String STRING_PATTERN = "%s:%s";
    private static final String KEY = "Authorization";

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public static Map<String, String> getAuthHeaders(@NonNull final String username, @NonNull final String password) {
        final Map<String, String> requestParams = new HashMap<>(1);
        final String credentials = String.format(STRING_PATTERN, username, password);
        final String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);

        requestParams.put(KEY, auth);

        return requestParams;
    }
}
