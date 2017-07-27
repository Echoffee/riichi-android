package com.echo171.riichimahjong.mahjong;

/**
 * Created by echo on 19/06/2017.
 */
public enum Family {
    MAN(0),
    SOU(1),
    PIN(2),
    WIND(3),
    DRAGON(4);

    private final int value;
    Family(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}