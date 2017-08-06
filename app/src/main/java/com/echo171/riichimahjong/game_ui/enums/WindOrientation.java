package com.echo171.riichimahjong.game_ui.enums;

public enum WindOrientation {
    EAST(0),
    SOUTH(1),
    WEST(2),
    NORTH(3);

    private final int value;
    WindOrientation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
