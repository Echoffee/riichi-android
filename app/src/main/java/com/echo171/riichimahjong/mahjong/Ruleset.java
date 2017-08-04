package com.echo171.riichimahjong.mahjong;

import com.echo171.riichimahjong.mahjong.enums.PlayerType;

import java.util.LinkedList;

/**
 * Created by echo on 19/06/2017.
 */
public class Ruleset {
    public int playersCount;
    public int startingScore;
    public PlayerType[] players;

    public Ruleset()
    {
        playersCount = 4;
        startingScore = 25000;
        players = new PlayerType[]{PlayerType.HUMAN_SELF, PlayerType.CPU_AUTO, PlayerType.CPU_AUTO, PlayerType.CPU_AUTO};
    }
}