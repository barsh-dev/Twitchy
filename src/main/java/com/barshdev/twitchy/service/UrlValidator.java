package com.barshdev.twitchy.service;

import com.barshdev.twitchy.exceptions.InvalidArgException;

import java.util.regex.Pattern;

public class UrlValidator {
    private static final Pattern URL_PATTERN = Pattern.compile(
            "((http|https)://)(www.)?"
                    + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                    + "{2,256}\\.[a-z]"
                    + "{2,6}\\b([-a-zA-Z0-9@:%"
                    + "._\\+~#?&//=]*)"
    );

    public static void isValidUrl(String url) {
        boolean isValid = true;
        if (url == null || url.trim().isEmpty()) {
            isValid = false;
        }
        else {
        isValid = URL_PATTERN.matcher(url).matches();
        }
        if (!isValid) {
            throw new InvalidArgException("Invalid URL Format");
        }
    }
}
