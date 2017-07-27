package com.echo171.riichimahjong.mahjong;

/**
 * Created by echo on 19/06/2017.
 */
public enum Side {
    EAST(0),
    SOUTH(1),
    WEST(2),
    NORTH(3);

    private final int value;
    Side(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}