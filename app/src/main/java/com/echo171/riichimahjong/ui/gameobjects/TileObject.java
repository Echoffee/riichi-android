package com.echo171.riichimahjong.ui.gameobjects;

import android.graphics.Bitmap;
import com.echo171.riichimahjong.mahjong.Tile;
import com.echo171.riichimahjong.ui.enums.TileOrientation;

public class TileObject extends GameObject {
    public Tile tile;

    public Bitmap getTileBitmap(TileOrientation orientation) {
        //TODO
        return null;
    }

    public TileOrientation getOrientation() {
        //TODO
        return null;
    }

    public boolean isHidden() {
        return tile.isHidden();
    }
}
