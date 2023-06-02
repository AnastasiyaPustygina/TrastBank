package com.example.trastbank.domain;

public class SMS {
    private final String text;
    private final String info;

    public SMS(String text, String info) {
        this.text = text;
        this.info = info;
    }

    public String getText() {
        return text;
    }

    public String getInfo() {
        return info;
    }
}
