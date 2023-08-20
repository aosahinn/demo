/*
package com.preschool.demo.utils;

import com.aksigorta.domain.Token;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TokenUtils {

    public static final String TOKEN_NAME = "Access-Token";
    public static final String REFRESH_TOKEN_NAME = "Refresh-Token";
    public static final String CLIENT_ID = "client-id";
    public static final String MOBILE_CLIENT = "mobile_client";
    public static final String WEB_CLIENT = "web_client";
    public static final String DEFAULT_CLIENT_ID = MOBILE_CLIENT;
    public static final String AGENCY_CODE = "agencyCode";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final Set<String> CLIENT_IDS = Stream.of(MOBILE_CLIENT, WEB_CLIENT).collect(Collectors.toSet());

    private static SecureRandom random = new SecureRandom();

    private TokenUtils() {

    }

    public static String generateToken() {
        return new BigInteger(130, random).toString(32);
    }

    public static String encodeBase64(String token) {
        return Base64.encodeBase64String(StringUtils.getBytesUtf8(token));
    }

    public static String decodeBase64(String token) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(token));
    }

    public static Token base64Token(Token token) {
        return new TokenBase64View(token);
    }

    public static  boolean isMobileClient(Token token){
        return MOBILE_CLIENT.equals(token.getClientId());
    }
}
*/
