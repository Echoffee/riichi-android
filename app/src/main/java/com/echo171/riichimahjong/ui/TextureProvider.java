package com.echo171.riichimahjong.ui;

import android.content.res.Resources;
import android.graphics.*;
import com.echo171.riichimahjong.R;
import com.echo171.riichimahjong.ui.enums.TileFamily;
import com.echo171.riichimahjong.ui.enums.TileOrientation;
import com.echo171.riichimahjong.ui.enums.WindOrientation;

import java.util.HashMap;

public class TextureProvider {
    private static  Bitmap tileVSelf;
    private static Bitmap tileVRight;
    private static Bitmap tileVFront;
    private static Bitmap tileVLeft;
    private static Bitmap tileHSelf;
    private static Bitmap tileHRight;
    private static Bitmap tileHFront;
    private static Bitmap tileHLeft;
    private static Bitmap tileRSelf;
    private static Resources resources;
    private static Paint paint;
    private static float m;
    private static HashMap<TileQuery, Bitmap> bitmapPool;

    public static void initialize(Resources res)
    {
        resources = res;
        tileVSelf = BitmapFactory.decodeResource(resources, R.drawable.front);
        tileVRight = BitmapFactory.decodeResource(resources, R.drawable.sider);
        tileVFront = BitmapFactory.decodeResource(resources, R.drawable.back);
        tileVLeft = BitmapFactory.decodeResource(resources, R.drawable.sidel);
        tileHSelf = BitmapFactory.decodeResource(resources, R.drawable.frontr);
        tileRSelf = BitmapFactory.decodeResource(resources, R.drawable.backr);
        tileHRight = BitmapFactory.decodeResource(resources, R.drawable.fronts);
        tileHFront = tileHSelf; //BitmapFactory.decodeResource(resources, R.drawable.frontr);
        tileHLeft = tileHRight; //BitmapFactory.decodeResource(resources, R.drawable.fronts);
        paint = new Paint();
        m = 0.8f;
        bitmapPool = new HashMap<>();
    }

    public static Bitmap getTileTexture(TileQuery query){
        Bitmap result = bitmapPool.get(query);
        if (result == null){
            result = createBitmap(getTextureId(query), query.getOrientation());
            bitmapPool.put(query, result);
        }

        return result;
    }

    private static int getTextureId(TileQuery query)
    {
        TileFamily family = query.getFamily();
        int value = query.getValue();
        boolean red = query.isRed();
        switch (family){
            default: return 0;
            case MAN:
                switch (value){
                    default : case  1: return R.drawable.man1;
                    case 2 : return R.drawable.man2;
                    case 3 : return R.drawable.man3;
                    case 4 : return R.drawable.man4;
                    case 5 : return (red?R.drawable.man5d : R.drawable.man5);
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
                    case 5 : return (red?R.drawable.sou5d : R.drawable.sou5);
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
                    case 5 : return (red?R.drawable.pin5d : R.drawable.pin5);
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
    }


    public static Bitmap createBitmap(int textureId, TileOrientation orientation)
    {
        Bitmap result;
        Canvas tileCanvas;
        Bitmap bb;
        Matrix mm;
        switch (orientation) {
            default:
                return null;
            case V_SELF:
                result = tileVSelf.copy(Bitmap.Config.ARGB_8888, true);
                tileCanvas = new Canvas(result);
                Bitmap bbb = BitmapFactory.decodeResource(resources, textureId);
                tileCanvas.drawBitmap(bbb, new Rect(0, 0, result.getWidth(), result.getHeight()), new RectF(0, 0, result.getWidth(), result.getHeight()), paint);
                break;
            case H_SELF:
                result = tileHSelf.copy(Bitmap.Config.ARGB_8888, true);
                tileCanvas = new Canvas(result);
                tileCanvas.drawBitmap(BitmapFactory.decodeResource(resources, textureId), new Rect(0, 0, result.getWidth(), result.getHeight()), new RectF(0, 0 - result.getHeight()*m/5, result.getWidth(), result.getHeight()- result.getHeight()*m/5), paint);
                break;
            case R_SELF:
                result = tileRSelf.copy(Bitmap.Config.ARGB_8888, true);
                break;
            case V_RIGHT:
                result = tileVRight.copy(Bitmap.Config.ARGB_8888, true);
                break;
            case H_RIGHT:
                result = tileHRight.copy(Bitmap.Config.ARGB_8888, true);
                tileCanvas = new Canvas(result);
                bb = BitmapFactory.decodeResource(resources, textureId);
                mm = new Matrix();
                mm.postRotate(-90, 0, 0);
                mm.postTranslate(-result.getWidth() * m / 5, result.getHeight() * m);
                tileCanvas.drawBitmap(bb, mm, paint);
                break;
            case V_FRONT:
                result = tileVFront.copy(Bitmap.Config.ARGB_8888, true);
                break;
            case H_FRONT:
                result = tileHFront.copy(Bitmap.Config.ARGB_8888, true);
                tileCanvas = new Canvas(result);
                bb = BitmapFactory.decodeResource(resources, textureId);
                mm = new Matrix();
                mm.postRotate(-180, 0, 0);
                mm.postTranslate(result.getWidth(), result.getHeight() * m * 1.2f);
                tileCanvas.drawBitmap(bb, mm, paint);
                break;
            case V_LEFT:
                result = tileVLeft.copy(Bitmap.Config.ARGB_8888, true);
                break;
            case H_LEFT:
                result = tileHLeft.copy(Bitmap.Config.ARGB_8888, true);
                tileCanvas = new Canvas(result);
                bb = BitmapFactory.decodeResource(resources, textureId);
                mm = new Matrix();
                mm.postRotate(-270, 0, 0);
                mm.postTranslate(result.getWidth() + result.getWidth()*m/5, 0);
                tileCanvas.drawBitmap(bb, mm, paint);
                break;
        }

        return result;
    }

    public static int getWindTextureId(WindOrientation wind) {
        switch (wind) {
            default: case EAST:
                return R.drawable.wind1;
            case SOUTH:
                return R.drawable.wind2;
            case WEST:
                return R.drawable.wind3;
            case NORTH:
                return R.drawable.wind4;
        }
    }

    public static Bitmap getGenericTileTexture(TileOrientation orientation) {
        switch (orientation) {
            case R_SELF:
                return tileRSelf;
            case V_RIGHT:
                return tileVRight;
            case V_FRONT:
                return tileVFront;
            case V_LEFT:
                return tileVLeft;
        }

        return null;
    }
}
