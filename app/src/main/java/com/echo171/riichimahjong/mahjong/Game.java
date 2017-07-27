package com.echo171.riichimahjong.mahjong;

import java.util.LinkedList;

/**
 * Created by echo on 19/06/2017.
 */
public class Game {
    private LinkedList<Player> players;
    private TileSet wall;
    private TileSet deadWall;
    private Side currentWind;
    private int round;
    private int bonus;
    private int hostId = 0;
    private Side currentPlayerTurn;


    public Side getCurrentWind() {
        return currentWind;
    }

    public int getRound() {
        return round;
    }

    public int getBonus() {
        return bonus;
    }

    public Side getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public Game()
    {
        players = new LinkedList<>();
        wall = new TileSet(Disposition.WALL);
        deadWall = new TileSet(Disposition.DEAD_WALL);
    }

    public boolean initialize(Ruleset rules)
    {
        //TODO : Don't fix PlayerType here
        //Init players
        for (int i = 0; i < rules.playersCount; i++)
            players.add(new Player(Side.values()[i], (i == 0 ? PlayerType.HUMAN_SELF : PlayerType.CPU_AUTO)));

        //Generate whole tileset
        if (!wall.createFullTileset())
            return false;

        //Initial draw 4 + 4 + 4 + 1/2
        for (int i = 0; i < 3; i++) {
            for (Player p: players) {
                for (int j = 0; j < 4; j++) {
                    p.addTileToHand(wall.pickTile());
                }
            }
        }

        for (Player p : players)
        {
            p.addTileToHand(wall.pickTile());
            p.getHand().sort();
        }

        //players.get(0).addTileToHand(wall.pickTile());
        //players.get(0).setSelectedTile(13);

        //Reveal dora	  KDDDDD   3579
        //				KKKUUUUU   124680
        for (int i = 0; i < 14; i++) {
            deadWall.addTile(wall.pickTile());
        }

        deadWall.revealTile(5);

        for(Player p : players)
            p.setScore(rules.startingScore);


		/*
		DEBUG
		 */

		/*TileSet e = new TileSet(Disposition.PON);
		Tile t = new Tile(Family.DRAGON, 3);
		e.addTile(t);
		e.addTile(new Tile(Family.DRAGON, 3));
		e.addTile(new Tile(Family.DRAGON, 3));
		t.setOrientation(TileOrientation.H_LEFT);
		players.get(0).getMelds().add(e);
		players.get(0).getHand().pickTile();
		players.get(0).getHand().pickTile();
		players.get(0).getHand().pickTile();*/
        //UiController.setLocalPlayerId(0);
        currentPlayerTurn = Side.EAST;

        //ON EACH DEVICE
        //UiManager m = new UiManager(this);
        //m.initialize();
        nextPlayerTurn();
        return true;
    }

    public Player getPlayer(Side playerSide) {
        return players.get(playerSide.getValue());
    }

    public TileSet getDeadWall() {
        return deadWall;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public void nextPlayerTurn() {
        switch (currentPlayerTurn) {
            case EAST:
                currentPlayerTurn = Side.SOUTH;
                break;
            case SOUTH:
                currentPlayerTurn = Side.WEST;
                break;
            case WEST:
                currentPlayerTurn = Side.NORTH;
                break;
            case NORTH:
                currentPlayerTurn = Side.EAST;
                break;
        }
        Player p = getPlayer(currentPlayerTurn);
        p.getHand().addTile(wall.pickTile());
        p.setSelectedTile(p.getHand().getTiles().size() - 1);
        //if (p.getPlayerType() == PlayerType.CPU_AUTO) {
        //DiscardTile.CPUExecute(this, currentPlayerTurn);
        //	endTurn();
        //}

        endTurn();
    }

    public void endTurn(){
        //if (UiController.checkEvents(this, currentPlayerTurn))
        //	UiController.executeEvents(this, currentPlayerTurn);		//To be executed on all devices
        //else
        nextPlayerTurn();

    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }
}