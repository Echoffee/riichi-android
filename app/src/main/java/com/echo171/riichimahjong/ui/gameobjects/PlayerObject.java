package com.echo171.riichimahjong.ui.gameobjects;

import com.echo171.riichimahjong.mahjong.Player;
import com.echo171.riichimahjong.mahjong.Tile;
import com.echo171.riichimahjong.ui.GameInstance;
import com.echo171.riichimahjong.ui.TileMapper;
import com.echo171.riichimahjong.ui.enums.PlayerPosition;
import com.echo171.riichimahjong.ui.enums.WindOrientation;

import java.util.LinkedList;

public class PlayerObject {
    private Player player;

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
        int delta = GameInstance.getLocalPlayer().getWind().getValue() - getWind().getValue();
        if (delta < 0)
            delta = 4 - delta;

        return PlayerPosition.values()[delta];
    }

    public int getScore() {
        return player.getScore();
    }

    public LinkedList<TileObject> getHandTiles() {
        //Mapper usage
        return TileMapper.getTiles(player.getHand());
    }

    public int getSelectedTileIndex() {
        if (selectedTileIndex == -1)
            selectedTileIndex = getHandTiles().size() - 1; //tmp

        return selectedTileIndex;
    }

    public LinkedList<TileObject> getRiverTiles() {
        //Mapper usage
        return TileMapper.getTiles(player.getRiver());
    }

    public LinkedList<LinkedList<TileObject>> getMelds() {
        //Mapper usage
        LinkedList<LinkedList<TileObject>> result = new LinkedList<>();
        for (int i = 0; i < player.getMelds().size(); i++)
            result.add(TileMapper.getTiles(player.getMelds().get(i)));

        return result;
    }
}
