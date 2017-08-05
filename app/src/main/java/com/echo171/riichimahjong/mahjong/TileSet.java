package com.echo171.riichimahjong.mahjong;

import com.echo171.riichimahjong.mahjong.enums.Disposition;
import com.echo171.riichimahjong.mahjong.enums.Family;
import com.echo171.riichimahjong.mahjong.enums.Position;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by echo on 19/06/2017.
 */
public class TileSet {
    private LinkedList<Tile> tiles;
    private Disposition disposition;
    private boolean revealed;

    public TileSet(Disposition disposition)
    {
        tiles = new LinkedList<>();
        this.disposition = disposition;
        revealed = false;
    }

    public void sort()
    {
        Collections.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1.getFamily() != o2.getFamily())
                    return o1.getFamily().getValue() - o2.getFamily().getValue();
                else
                    return o1.getValue() - o2.getValue();
            }
        });
    }

    public boolean createFullTileset() {
        LinkedList<Tile> set = new LinkedList<>();
        for (Family f : Family.values())
        {
            switch (f)
            {
                case MAN:
                case SOU:
                case PIN:
                    for (int i = 1; i < 10; i++) {
                        Tile t = new Tile(f, i);
                        if (i == 5)
                            t.setDoraValue(1);

                        t.setPosition(Position.WALL);
                        set.add(t);
                        for (int j = 0; j < 3; j++){
                            Tile tt = new Tile(f, i);
                            tt.setPosition(Position.WALL);
                            set.add(tt);
                        }
                    }
                    break;
                case WIND:
                case DRAGON:
                    for (int i = 1; i < 5; i++)
                        for (int j = 0; j < 4; j++){
                            Tile t = new Tile(f, i);
                            t.setPosition(Position.WALL);
                            set.add(t);
                        }
                    break;
            }
        }

        Random rs = new Random();
        while (set.size() > 0)
        {
            int index = rs.nextInt(set.size());
            Tile t = set.remove(index);
            tiles.add(t);
        }

        return true;
    }

    public Tile pickTile()
    {
        return tiles.removeLast();
    }

    public void addTile(Tile t, Position newPosition)
    {
        t.setPosition(newPosition);
        tiles.addLast(t);
    }

    public void revealTile(int index)
    {
        tiles.get(index).reveal();
    }


    public LinkedList<Tile> getTiles() {
        return tiles;
    }
}