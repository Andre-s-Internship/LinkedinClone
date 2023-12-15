package com.andresinternship.linkedinclone.service.helper;

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

}
