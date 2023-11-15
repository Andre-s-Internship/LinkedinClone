package com.andresinternship.linkedinclone.model;

public class CustomJWTToken {

    private final String header;
    private final String payload;
    private final String signature;

    public CustomJWTToken(String header, String payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return header + "." + payload + "." + signature;
    }

    public static TokenGenerator create() {
        return new TokenGenerator();
    }

}
