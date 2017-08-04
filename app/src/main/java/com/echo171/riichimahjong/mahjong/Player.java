package com.echo171.riichimahjong.mahjong;

import com.echo171.riichimahjong.mahjong.enums.Disposition;
import com.echo171.riichimahjong.mahjong.enums.PlayerType;
import com.echo171.riichimahjong.mahjong.enums.Wind;

import java.util.LinkedList;

/**
 * Created by echo on 19/06/2017.
 */
public class Player {

    private int score;
    private TileSet hand;
    private LinkedList<TileSet> melds;
    private TileSet river;
    private Wind wind;
    private boolean isRiichi;
    private int selectedTile;
    private PlayerType playerType;

    public Player(Wind wind, PlayerType playerType)
    {
        this.wind = wind;
        this.playerType = playerType;
        score = 0;
        hand = new TileSet(Disposition.HAND_PLAYER);
        melds = new LinkedList<>();
        river = new TileSet(Disposition.RIVER);
        isRiichi = false;
    }
    public void addTileToHand(Tile tile)
    {
        hand.addTile(tile);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TileSet getHand() {
        return hand;
    }

    public LinkedList<TileSet> getMelds() {
        return melds;
    }

    public TileSet getRiver() {
        return river;
    }

    public Wind getWind() {
        return wind;
    }

    public boolean isRiichi() {
        return isRiichi;
    }

/*    public int getWindTextureID(){
        switch (wind){
            default : case  EAST: return R.drawable.wind1;
            case SOUTH: return R.drawable.wind2;
            case WEST: return R.drawable.wind3;
            case NORTH: return R.drawable.wind4;
        }
    }*/

    public int getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(int selectedTile) {
        this.selectedTile = selectedTile;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}