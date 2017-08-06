package com.echo171.riichimahjong.game_ui.gameobjects;

import android.graphics.Bitmap;
import com.echo171.riichimahjong.mahjong.Tile;
import com.echo171.riichimahjong.game_ui.GameInstance;
import com.echo171.riichimahjong.game_ui.TextureProvider;
import com.echo171.riichimahjong.game_ui.enums.*;
import com.echo171.riichimahjong.game_ui.TileQuery;

public class TileObject extends GameObject {
    private Tile tile;
    private Bitmap bitmapCache;
    private TileOrientation previousOrientation;
    private TilePosition position;

    public TileObject(Tile tile, TilePosition position){
        this.tile = tile;
        this.position= position;
        previousOrientation = TileOrientation.NONE;
        generateTextures();
    }

    public Bitmap getTileBitmap(TileOrientation orientation) {
        if (orientation == previousOrientation)
            return bitmapCache;

        TileFamily family;
        int value = tile.getValue();
        boolean red = (tile.getDoraValue() == 1);
        switch (tile.getFamily()) {
            default: return null;
            case MAN:
                family = TileFamily.MAN;
                break;
            case SOU:
                family = TileFamily.SOU;
                break;
            case PIN:
                family = TileFamily.PIN;
                break;
            case WIND:
                family = TileFamily.WIND;
                break;
            case DRAGON:
                family = TileFamily.DRAGON;
                break;
        }

        TileQuery q = new TileQuery(family, value, red);
        q.setOrientation(orientation);
        Bitmap result = TextureProvider.getTileTexture(q);
        previousOrientation = orientation;
        bitmapCache = result;
        return bitmapCache;
    }

    public TileOrientation getOrientation() {
        switch (position) {
            case WALL:
                return TileOrientation.R_SELF;

            case DEAD_WALL:
                return (tile.isHidden()?TileOrientation.R_SELF: TileOrientation.H_SELF);

            case HAND_EAST:
                return getOrientationFromRelativePosition(WindOrientation.EAST, true);

            case HAND_SOUTH:
                return getOrientationFromRelativePosition(WindOrientation.SOUTH, true);

            case HAND_WEST:
                return getOrientationFromRelativePosition(WindOrientation.WEST, true);

            case HAND_NORTH:
                return getOrientationFromRelativePosition(WindOrientation.NORTH, true);

            case MELD_EAST:
            case RIVER_EAST:
                return getOrientationFromRelativePosition(WindOrientation.EAST, false);

            case MELD_SOUTH:
            case RIVER_SOUTH:
                return getOrientationFromRelativePosition(WindOrientation.SOUTH, false);

            case MELD_WEST:
            case RIVER_WEST:
                return getOrientationFromRelativePosition(WindOrientation.WEST, false);

            case MELD_NORTH:
            case RIVER_NORTH:
                return getOrientationFromRelativePosition(WindOrientation.NORTH, false);
            }

        return previousOrientation;
    }

    public boolean isHidden() {
        return tile.isHidden();
    }

    private PlayerPosition getRelativePositionFromSelf(WindOrientation otherPlayer){
        int delta = otherPlayer.getValue() - GameInstance.getLocalPlayer().getWind().getValue();
        if (delta < 0)
            delta = 3 - GameInstance.getLocalPlayer().getWind().getValue();

        switch (delta){
            default:return null;
            case 0: return PlayerPosition.SELF;
            case 1: return PlayerPosition.RIGHT;
            case 2: return PlayerPosition.FRONT;
            case 3: return PlayerPosition.LEFT;
        }
    }

    private TileOrientation getOrientationFromRelativePosition(WindOrientation orientation, boolean vertical){
        PlayerPosition position = getRelativePositionFromSelf(orientation);
        if (position == null)
            return null;

        switch (position) {
            default: return null;
            case SELF:
                return (vertical ? TileOrientation.V_SELF:TileOrientation.H_SELF);
            case RIGHT:
                return (vertical ? TileOrientation.V_RIGHT:TileOrientation.H_RIGHT);
            case FRONT:
                return (vertical ? TileOrientation.V_FRONT:TileOrientation.H_FRONT);
            case LEFT:
                return (vertical ? TileOrientation.V_LEFT:TileOrientation.H_LEFT);
        }
    }

    private void generateTextures(){

    }
}
