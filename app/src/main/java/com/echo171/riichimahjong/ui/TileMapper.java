package com.echo171.riichimahjong.ui;

import com.echo171.riichimahjong.mahjong.Tile;
import com.echo171.riichimahjong.mahjong.TileSet;
import com.echo171.riichimahjong.ui.gameobjects.TileObject;

import java.util.HashMap;
import java.util.LinkedList;

public class TileMapper {
    private static HashMap<Tile, TileObject> tiles;

    public static void initialize(){
        tiles = new HashMap<>();
    }

    public static TileObject getTile(Tile tile){
        //TODO: Add TilePosition
        TileObject result = tiles.get(tile);
        if (result == null){
            result = new TileObject(tile);
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
