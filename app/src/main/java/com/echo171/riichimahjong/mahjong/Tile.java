package com.echo171.riichimahjong.mahjong;

import com.echo171.riichimahjong.mahjong.enums.Family;

/**
 * Created by echo on 19/06/2017.
 */
public class Tile{//} extends IMovableObject{

    /*
    Value : Man, Pin, Sou : 1-9;
    		Winds : 1E, 2S, 3W, 4N;
    		Dragons : 1R, 2B, 3G;
     */

    private int value;
    private Family family;
    private int doraValue;
    private boolean revealed;
    //private TileOrientation orientation;
    //private Bitmap tileBitmap;
    private boolean picked;

    public Tile(Family family, int value)
    {
        this.family = family;
        this.value = value;
        doraValue = 0;
        revealed = false;
        //orientation = TileOrientation.NONE;
    }


    public int getDoraValue() {
        return doraValue;
    }

    public void setDoraValue(int doraValue) {
        this.doraValue = doraValue;
    }

    public void reveal() {
        revealed = true;
    }

    public void hide(){
        revealed = false;
    }

/*    public int getTextureId()
    {
        switch (family){
            default:
            case MAN:
                switch (value){
                    default : case  1: return R.drawable.man1;
                    case 2 : return R.drawable.man2;
                    case 3 : return R.drawable.man3;
                    case 4 : return R.drawable.man4;
                    case 5 : return (doraValue > 0 ?R.drawable.man5d : R.drawable.man5);
                    case 6 : return R.drawable.man6;
                    case 7 : return R.drawable.man7;
                    case 8 : return R.drawable.man8;
                    case 9 : return R.drawable.man9;
                }
            case SOU:
                switch (value){
                    default : case  1: return R.drawable.sou1;
                    case 2 : return R.drawable.sou2;
                    case 3 : return R.drawable.sou3;
                    case 4 : return R.drawable.sou4;
                    case 5 : return (doraValue > 0 ?R.drawable.sou5d : R.drawable.sou5);
                    case 6 : return R.drawable.sou6;
                    case 7 : return R.drawable.sou7;
                    case 8 : return R.drawable.sou8;
                    case 9 : return R.drawable.sou9;
                }
            case PIN:
                switch (value){
                    default : case  1: return R.drawable.pin1;
                    case 2 : return R.drawable.pin2;
                    case 3 : return R.drawable.pin3;
                    case 4 : return R.drawable.pin4;
                    case 5 : return (doraValue > 0 ?R.drawable.pin5d : R.drawable.pin5);
                    case 6 : return R.drawable.pin6;
                    case 7 : return R.drawable.pin7;
                    case 8 : return R.drawable.pin8;
                    case 9 : return R.drawable.pin9;
                }
            case WIND:
                switch (value){
                    default : case  1: return R.drawable.wind1;
                    case 2 : return R.drawable.wind2;
                    case 3 : return R.drawable.wind3;
                    case 4 : return R.drawable.wind4;
                }
            case DRAGON:
                switch (value){
                    default : case  1: return R.drawable.dragon1;
                    case 2 : return R.drawable.dragon2;
                    case 3 : return R.drawable.dragon3;
                }
        }
    }*/

    public boolean isHidden() {
        return !revealed;
    }

    /*public void setTileBitmap(Bitmap tileBitmap, TileOrientation newOrientation)
    {
        this.tileBitmap = tileBitmap;
        this.orientation = newOrientation;
    }

    public Bitmap getTileBitmap(TileOrientation orientation)
    {
        if (orientation != this.orientation || tileBitmap == null)
        {
            tileBitmap = TileGenerator.createBitmap(getTextureId(), orientation);
            this.orientation = orientation;
        }

        return tileBitmap;
    }*/

    public Family getFamily() {
        return family;
    }

    public int getValue() {
        return value;
    }

    /*public TileOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(TileOrientation orientation) {
        this.orientation = orientation;
    }
*/
    public void setPicked(boolean picked) {
        this.picked = picked;
    }
}