package com.echo171.riichimahjong.game_ui;

import com.echo171.riichimahjong.mahjong.Tile;
import com.echo171.riichimahjong.mahjong.TileSet;
import com.echo171.riichimahjong.game_ui.enums.TilePosition;
import com.echo171.riichimahjong.game_ui.gameobjects.TileObject;

import java.util.HashMap;
import java.util.LinkedList;

public class TileMapper {
    private static HashMap<Tile, TileObject> tiles;

    public static void initialize(){
        tiles = new HashMap<>();
    }

    public static TileObject getTile(Tile tile){
        TileObject result = tiles.get(tile);
        if (result == null){
            TilePosition p = null;
            switch (tile.getPosition()) {
                case WALL:
                    p = TilePosition.WALL;
                    break;
                case DEAD_WALL:
                    p = TilePosition.DEAD_WALL;
                    break;
                case HAND_EAST:
                    p = TilePosition.HAND_EAST;
                    break;
                case HAND_SOUTH:
                    p = TilePosition.HAND_SOUTH;
                    break;
                case HAND_WEST:
                    p = TilePosition.HAND_WEST;
                    break;
                case HAND_NORTH:
                    p = TilePosition.HAND_NORTH;
                    break;
                case MELD_EAST:
                    p = TilePosition.MELD_EAST;
                    break;
                case MELD_SOUTH:
                    p = TilePosition.MELD_SOUTH;
                    break;
                case MELD_WEST:
                    p = TilePosition.MELD_WEST;
                    break;
                case MELD_NORTH:
                    p = TilePosition.MELD_NORTH;
                    break;
                case RIVER_EAST:
                    p = TilePosition.RIVER_EAST;
                    break;
                case RIVER_SOUTH:
                    p = TilePosition.RIVER_SOUTH;
                    break;
                case RIVER_WEST:
                    p = TilePosition.RIVER_WEST;
                    break;
                case RIVER_NORTH:
                    p = TilePosition.RIVER_NORTH;
                    break;
            }

            result = new TileObject(tile, p);
        }

        return result;
    }

    public static LinkedList<TileObject> getTiles(TileSet tiles){
        LinkedList<TileObject> result = new LinkedList<>();
        for(Tile t : tiles.getTiles())
            result.add(getTile(t));

        return result;
    }
}
