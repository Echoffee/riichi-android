package com.echo171.riichimahjong.game_ui.enums;

public enum PlayerPosition {
    SELF(0),
    RIGHT(1),
    FRONT(2),
    LEFT(3);
    private final int value;
    PlayerPosition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
