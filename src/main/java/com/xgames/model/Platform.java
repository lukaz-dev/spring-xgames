package com.xgames.model;

public enum Platform {
    PLAYSTATION_3("PlayStation 3"),
    PLAYSTATION_4("PlayStation 4"),
    XBOX_360("XBOX 360"),
    XBOX_ONE("XBOX One"),
    NINTENDO_SWITCH("Nintendo Switch"),
    PC("PC");

    private String description;

    Platform(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
