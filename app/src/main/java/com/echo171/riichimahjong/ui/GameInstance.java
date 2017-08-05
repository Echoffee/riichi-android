package com.echo171.riichimahjong.ui;

import com.echo171.riichimahjong.mahjong.Game;
import com.echo171.riichimahjong.ui.enums.PlayerPosition;
import com.echo171.riichimahjong.ui.enums.WindOrientation;
import com.echo171.riichimahjong.ui.gameobjects.PlayerObject;
import com.echo171.riichimahjong.ui.gameobjects.TileObject;

import java.util.LinkedList;

public class GameInstance {
    private static Game game;
    private static LinkedList<PlayerObject> playerObjects;
    private static PlayerObject localPlayer;

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameInstance.game = game;
    }

    public static LinkedList<PlayerObject> getPlayers() {
        return playerObjects;
    }

    public static PlayerObject getPlayer(PlayerPosition side){
        for(PlayerObject po : playerObjects)
            if (po.getPosition() == side)
                return po;

        return null;
    }

    public static PlayerObject getLocalPlayer() {
        return localPlayer;
    }

    public static int getPositionDelta(PlayerPosition s) {
        int localPosition = s.getValue() - localPlayer.getWind().getValue();
        if (localPosition < 0)
            localPosition = 4 - s.getValue();

        return localPosition;
    }

    public static WindOrientation getCurrentPlayerTurn() {
        switch (game.getCurrentPlayerTurn()) {
            case EAST:
                return WindOrientation.EAST;
            case SOUTH:
                return WindOrientation.SOUTH;
            case WEST:
                return WindOrientation.WEST;
            case NORTH:
                return WindOrientation.NORTH;
        }

        return null;
    }

    public static LinkedList<TileObject> getDeadWallTiles() {
        //TODO: Same issue as for PlayerObject : when to create TileObjects ?
        return TileMapper.getTiles(game.getDeadWall());
    }
}
