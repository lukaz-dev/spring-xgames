package com.xgames.model;

public enum Format {
    DIGITAL("Digital"), DISC("Física");

    private String description;

    Format(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
