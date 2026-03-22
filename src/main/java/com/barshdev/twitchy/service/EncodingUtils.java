package com.barshdev.twitchy.service;

import com.barshdev.twitchy.exceptions.InvalidArgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class EncodingUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncodingUtils.class);
    
    public static String encode(String actualStr) {
        return Base64.getEncoder().withoutPadding().encodeToString(actualStr
                    .getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String encodedStr) {
        try {
            return new String(Base64.getDecoder()
                    .decode(encodedStr), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Base64 encoded string");
            throw new InvalidArgException("encoded string is invalid");
        }
    }
}
