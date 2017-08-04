package com.echo171.riichimahjong.ui.gameobjects;

import com.echo171.riichimahjong.mahjong.Player;
import com.echo171.riichimahjong.ui.GameInstance;
import com.echo171.riichimahjong.ui.enums.PlayerPosition;
import com.echo171.riichimahjong.ui.enums.WindOrientation;

import java.util.LinkedList;

public class PlayerObject {
    private Player player;
    private GameInstance gameInstance;

    //ui-only properties
    private int selectedTileIndex = -1;

    public WindOrientation getWind(){
        switch (player.getWind()) {
            default:case EAST:
                return WindOrientation.EAST;
            case SOUTH:
                return WindOrientation.SOUTH;
            case WEST:
                return WindOrientation.WEST;
            case NORTH:
                return WindOrientation.NORTH;
        }
    }

    public PlayerPosition getPosition(){
        int delta = gameInstance.getLocalPlayer().getWind().getValue() - getWind().getValue();
        if (delta < 0)
            delta = 4 - delta;

        return PlayerPosition.values()[delta];
    }

    public int getScore() {
        return player.getScore();
    }

    public LinkedList<TileObject> getHandTiles() {
        //TODO : When to generate TileObject ?
        return null;
    }

    public int getSelectedTileIndex() {
        if (selectedTileIndex == -1)
            selectedTileIndex = getHandTiles().size() - 1; //tmp

        return selectedTileIndex;
    }

    public LinkedList<TileObject> getRiverTiles() {
        //Todo : Same as getHandTiles()
        return null;
    }

    public LinkedList<LinkedList<TileObject>> getMelds() {
        //TODO : Not sure about that
        return null;
    }
}
