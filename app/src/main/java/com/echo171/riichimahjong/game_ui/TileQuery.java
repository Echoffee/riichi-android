package com.echo171.riichimahjong.game_ui;

import com.echo171.riichimahjong.game_ui.enums.TileFamily;
import com.echo171.riichimahjong.game_ui.enums.TileOrientation;

public class TileQuery {

    private TileFamily family;
    private int value;
    private boolean red;
    TileOrientation orientation;

    public TileQuery(TileFamily family, int value, boolean red){
        this.family = family;
        this.value = value;
        this.red = red;
    }

    public void setOrientation(TileOrientation orientation) {
        this.orientation = orientation;
    }

    public TileOrientation getOrientation() {
        return orientation;
    }

    public TileFamily getFamily() {
        return family;
    }

    public int getValue() {
        return value;
    }

    public boolean isRed() {
        return red;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TileQuery tileQuery = (TileQuery) o;

        if (value != tileQuery.value) return false;
        if (red != tileQuery.red) return false;
        if (family != tileQuery.family) return false;
        return orientation == tileQuery.orientation;
    }
}
