package com.andresinternship.linkedinclone.service.helper;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class TokenGenerator {

    private final String secretKey = "secretKey";
    private JSONObject payload = new JSONObject();
    private JSONObject header = new JSONObject();

    public TokenGenerator withAttribute(String attribute, String value) {
        payload.put(attribute, value);
        return this;
    }

    public TokenGenerator withExpiresAt(Date date) {
        payload.put("expires_at", date.toString());
        return this;
    }

    public TokenGenerator withIssuedAt(Date date) {
        payload.put("issued_at", date.toString());
        return this;
    }

    public void buildHeader() {
        header.put("alg", "HSA256");
        header.put("typ", "JWT");
    }

    private String createSignature(String header, String payload) {
        try {
            String token = header + "." + payload + secretKey;
            return hashString(token);
        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }
        return null;
    }

    private static String hashString(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String encodeJSON(JSONObject jsonObject) {
        String encoded = Base64.getEncoder().encodeToString(jsonObject.toString().getBytes());
        return encoded.replace(String.valueOf('='), "");
    }

    public String build() {
        buildHeader();
        String encodedHeader = encodeJSON(header);
        String encodedPayload = encodeJSON(payload);
        String signature = createSignature(encodedHeader, encodedPayload);
        CustomJWTToken customJWTToken = new CustomJWTToken(encodedHeader, encodedPayload, signature);
        return customJWTToken.toString();
    }

    public static TokenGenerator create() {
        return new TokenGenerator();
    }

    public static CustomJWTToken create(String header, String payload) {
        return new CustomJWTToken(header, payload, create().createSignature(header, payload));
    }

}
