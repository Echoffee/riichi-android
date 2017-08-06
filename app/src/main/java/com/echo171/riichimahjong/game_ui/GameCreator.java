package com.echo171.riichimahjong.game_ui;

import com.echo171.riichimahjong.game_ui.game.GameDrawer;
import com.echo171.riichimahjong.game_ui.gameobjects.PlayerObject;
import com.echo171.riichimahjong.mahjong.Game;
import com.echo171.riichimahjong.mahjong.Player;
import com.echo171.riichimahjong.mahjong.Ruleset;

import java.util.LinkedList;

public class GameCreator {
    public static void createDemoGame(){
        Game game = new Game();
        game.initialize(new Ruleset());
        GameInstance.setGame(game);

        LinkedList<PlayerObject> playerObjects = new LinkedList<>();
        for(Player p : game.getPlayers()){
            playerObjects.add(new PlayerObject(p));
        }

        GameInstance.setPlayerObjects(playerObjects);
        GameInstance.setLocalPlayer(playerObjects.get(0));

        TileMapper.initialize();
    }
}
