package com.genson;

public enum Secret {
    USERNAME("yourUsername"),
    PASSWORD("yourPassword");

    private final String value;

    Secret(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
