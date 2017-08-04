package com.echo171.riichimahjong.ui.game;

import android.content.res.Resources;
import android.graphics.*;
import com.echo171.riichimahjong.ui.GameInstance;
import com.echo171.riichimahjong.ui.TextureProvider;
import com.echo171.riichimahjong.ui.animations.GameObjectAnimator;
import com.echo171.riichimahjong.ui.controls.Button;
import com.echo171.riichimahjong.ui.enums.PlayerPosition;
import com.echo171.riichimahjong.ui.enums.TileOrientation;
import com.echo171.riichimahjong.ui.enums.WindOrientation;
import com.echo171.riichimahjong.ui.gameobjects.PlayerObject;
import com.echo171.riichimahjong.ui.gameobjects.TileObject;

import java.util.LinkedList;

/**
 * Created by echo on 19/06/2017.
 */
public class GameDrawer {
    private GameInstance gameInstance;
    private Resources resources;

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    private boolean dirty;

    private Paint paint;
    private float TILE_WIDTH_RATIO_L = 0.03125f;
    private float TILE_HEIGHT_RATIO_L = 0.088f;
    private float PLAYER_OTHER_SIZE_MODIFIER = 0.8f;
    private float PLAYER_SELF_SIZE_MODIFIER = 1.2f;

    private float tileWidthL = 0.03125f;
    private float tileHeightL = 0.088f;
    private float playerFrontPlacementX = 0.25f;//0.58046f;//0.25625f; //0.55f
    private float playerFrontPlacementY = 0.03333f;
    private float playerLeftPlacementX = 0.0125f;
    private float playerLeftPlacementY = 0.25f;//0.06111f;
    private float playerSelfPlacementX = 0.140625f;
    private float playerSelfPlacementY = 0.88888f;
    private float playerRightPlacementX = 0.7375f;//0.71875f;
    private float playerRightPlacementY = 0.25f;//0.30277f;

    private float deadWallPlacementX = 0.3125f;
    private float deadWallPlacementY = 0.50694f;

    private float playerFrontWindPlacementX = 0.443775f;//0.30625f;
    private float playerFrontWindPlacementY = 0.39444f;//0.36111f;
    private float playerLeftWindPlacementX = 0.28984f;
    private float playerLeftWindPlacementY = 0.37777f;
    private float playerSelfWindPlacementX = 0.30625f;
    private float playerSelfWindPlacementY = 0.60555f;
    private float playerRightWindPlacementX = 0.46093f;
    private float playerRightWindPlacementY = 0.62222f;

    private float playerSelfRiverPlacementX = 0.30078f;
    private float playerSelfRiverPlacementY = 0.64722f;
    private float playerRightRiverPlacementX = 0.49375f;
    private float playerRightRiverPlacementY = 0.62361f;//0.36111f;
    private float playerFrontRiverPlacementX = 0.45f;//0.42478f;
    private float playerFrontRiverPlacementY = 0.28472f;//0.35f;
    private float playerLeftRiverPlacementX = 0.22343f;
    private float playerLeftRiverPlacementY = 0.37638f;

    private float playerSelfMeldsPlacementX = 0.140625f + 0.6f;
    private float playerSelfMeldsPlacementY = 0.88888f;

    private LinkedList<Button> buttons;

    private float tileMovingSpeed = 9f;

    private Bitmap screen;


    public GameDrawer(GameInstance game) {
        this.gameInstance = game;
        //playerId = 4;
        paint = new Paint();
        paint.setAntiAlias(true);
        buttons = new LinkedList<>();
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void initialize(Canvas canvas) {


        //To migrate
		/*Button b = new Button(canvas);
		buttons.add(b);
		b.setPlacement(new RectF(0.8f, 0.8f, 0.95f, 0.95f));
		b.setType(ButtonType.ACTIVE);
		b.setColors(Color.rgb( 180, 0, 0), Color.rgb( 255, 0, 0));
		b.setText("Discard");
		b.setAction(new DiscardTile());*/

        /*Button b = new Button(canvas);
        buttons.add(b);
        b.setPlacement(new RectF(0.8f, 0.2f, 0.95f, 0.35f));
        b.setType(ButtonType.ACTIVE);
        b.setColors(Color.rgb(0, 0, 180), Color.rgb(0, 0, 255));
        b.setText("Pon");
        b.setAction(new CallPon());
        *///

        playerFrontPlacementX *= canvas.getWidth();
        playerLeftPlacementX *= canvas.getWidth();
        playerSelfPlacementX *= canvas.getWidth();
        playerRightPlacementX *= canvas.getWidth();
        deadWallPlacementX *= canvas.getWidth();
        playerFrontWindPlacementX *= canvas.getWidth();
        playerLeftWindPlacementX *= canvas.getWidth();
        playerSelfWindPlacementX *= canvas.getWidth();
        playerRightWindPlacementX *= canvas.getWidth();
        playerFrontPlacementY *= canvas.getHeight();
        playerLeftPlacementY *= canvas.getHeight();
        playerSelfPlacementY *= canvas.getHeight();
        playerRightPlacementY *= canvas.getHeight();
        deadWallPlacementY *= canvas.getHeight();
        playerFrontWindPlacementY *= canvas.getHeight();
        playerLeftWindPlacementY *= canvas.getHeight();
        playerSelfWindPlacementY *= canvas.getHeight();
        playerRightWindPlacementY *= canvas.getHeight();

        playerSelfRiverPlacementX *= canvas.getWidth();
        playerRightRiverPlacementX *= canvas.getWidth();
        playerFrontRiverPlacementX *= canvas.getWidth();
        playerLeftRiverPlacementX *= canvas.getWidth();
        playerSelfRiverPlacementY *= canvas.getHeight();
        playerRightRiverPlacementY *= canvas.getHeight();
        playerFrontRiverPlacementY *= canvas.getHeight();
        playerLeftRiverPlacementY *= canvas.getHeight();

        playerSelfMeldsPlacementX *= canvas.getWidth();
        playerSelfMeldsPlacementY *= canvas.getHeight();

        tileWidthL *= canvas.getWidth();
        tileHeightL *= canvas.getHeight();
        screen = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        dirty = true;
    }

    public void draw(Canvas canvas) {
        if (dirty)
        {
            Canvas c = new Canvas(screen);
            drawPlaymat(c);
            for (PlayerObject player : gameInstance.getPlayers())
            {
                if (player.getWind() != gameInstance.getLocalPlayer().getWind())
                    drawPlayerHandL(c, player);

                drawPlayerRiver(c, player);
                drawPlayerMelds(c, player);
            }

            drawDeadWallL(c);
            drawScores(c);
            newTurnAnimation(c);
            dirty = false;
        }

        canvas.drawBitmap(screen, 0, 0, paint);
        drawPlayerHandL(canvas, gameInstance.getLocalPlayer());
//        for (Button b : UiController.getUi())
//            b.draw(canvas);
    }

/*    public boolean handleTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (x > playerSelfPlacementX && y > playerSelfPlacementY)
        {
            int i = (int) ((x - playerSelfPlacementX) / (tileWidthL * PLAYER_SELF_SIZE_MODIFIER));
            if (i != gameInstance.getLocalPlayer().getPlayer().getSelectedTile() && i < MultiplayerManager.getLocalPlayer().getPlayer().getHand().getTiles().size())
            {
                GameObjectAnimator a = new GameObjectAnimator(MultiplayerManager.getLocalPlayer().getPlayer().getHand().getTiles().get(i), (int) (playerSelfPlacementX + i * tileWidthL * PLAYER_SELF_SIZE_MODIFIER), (int) playerSelfPlacementY);

                CImageView.addAnimatedObject(a);
                a.setGoal(a.getX(), a.getY() - (int)(tileHeightL/2), tileMovingSpeed);
                a.setBitmap(MultiplayerManager.getLocalPlayer().getPlayer().getHand().getTiles().get(i).getTileBitmap(TileOrientation.V_SELF));
                a.setDims(tileWidthL* PLAYER_SELF_SIZE_MODIFIER, tileHeightL * PLAYER_SELF_SIZE_MODIFIER);
                MultiplayerManager.getLocalPlayer().getPlayer().setSelectedTile(i);
                return true;
            }
        }

        boolean result = false;
        for (Button b : UiController.getUi())
            if (b.handleTouch(event, gameInstance))
                result = true;

        return result;
    }*/

    private void drawScores(Canvas canvas) {
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setTextSize(32);
        float mx = canvas.getWidth()/2;
        float my = canvas.getHeight()/2;
        //for (int i = 0; i < 4; i++) {
        for (PlayerPosition s : PlayerPosition.values()){
            Bitmap text = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
            Canvas writer = new Canvas(text);
            Matrix m = new Matrix();
            //int i = gameInstance.getPositionDelta(s);
            m.setRotate(-90 * s.getValue(), 400, 400);
            switch (s.getValue())
            {
                case 0: m.postTranslate(playerSelfWindPlacementX - 400, playerSelfWindPlacementY - 400);break;
                case 1: m.postTranslate(playerRightWindPlacementX - 400, playerRightWindPlacementY - 400);break;
                case 2: m.postTranslate(playerFrontWindPlacementX - 400, playerFrontWindPlacementY - 400);break;
                case 3: m.postTranslate(playerLeftWindPlacementX - 400, playerLeftWindPlacementY - 400);break;
            }
            Bitmap w = BitmapFactory.decodeResource(resources, TextureProvider.getWindTextureId(gameInstance.getPlayer(s).getWind()));
            writer.drawBitmap(w, new Rect(0, 0, w.getWidth(), w.getHeight()), new RectF(
                    400,
                    400,
                    400 + 0.01875f * canvas.getWidth()* 1.5f,
                    400 + 0.03333f * canvas.getHeight()* 2), paint);
            writer.drawText("" + gameInstance.getPlayer(s).getScore(), 400 + canvas.getWidth() * 0.078125f, 400 + 16 + (0.01875f * canvas.getWidth()* 1.5f)/2, paint);
            canvas.drawBitmap(text, m, paint);
        }

        //canvas.drawText("Round " + game.getCurrentWind() + " " + game.getRound(), mx, my - 0.05f * canvas.getHeight(), paint);
    }

    private void drawPlaymat(Canvas canvas) {
        //canvas.drawColor(Color.rgb(0, 160, 0));
        RadialGradient gradient = new RadialGradient(canvas.getWidth()* 0.375f, canvas.getHeight()* 0.375f, canvas.getHeight(), 0xff779977,
                0xff004200, android.graphics.Shader.TileMode.CLAMP);
        Paint p = new Paint();
        p.setDither(true);
        p.setShader(gradient);
        Bitmap b = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas ca = new Canvas(b);
        ca.drawCircle(canvas.getWidth() * 0.375f, canvas.getHeight() * 0.375f, canvas.getWidth(), p);
        canvas.drawBitmap(b, 0, 0,p);
        //Bitmap map = BitmapFactory.decodeResource(resources, R.drawable.scoringplace);
        //RectF r = new RectF( canvas.getWidth()/4, canvas.getHeight()/4,  canvas.getWidth()*0.75f, canvas.getHeight()*0.75f);
        //canvas.drawBitmap(map,new Rect(0, 0, map.getWidth(), map.getWidth()), r, paint);

    }

    private void drawPlayerHandL(Canvas canvas, PlayerObject player) {
        float x = 0;
        float y = 0;
        WindOrientation playerSide = player.getWind();
        float m = (player.getWind() == playerSide ? PLAYER_SELF_SIZE_MODIFIER : PLAYER_OTHER_SIZE_MODIFIER);
        //int delta = gameInstance.getPositionDelta(player.getPosition());
        PlayerPosition position = player.getPosition();
        switch (position)
        {
            case SELF: x = playerSelfPlacementX; y = playerSelfPlacementY;break;
            case RIGHT: x = playerRightPlacementX; y = playerRightPlacementY;break;
            case FRONT: x = playerFrontPlacementX; y = playerFrontPlacementY;break;
            case LEFT: x = playerLeftPlacementX; y = playerLeftPlacementY;break;
        }

        RectF rect;
        if (position ==  PlayerPosition.SELF|| position == PlayerPosition.FRONT)
            rect = new RectF(x, y, x + tileWidthL * m, y + tileHeightL * m);
        else
            rect = new RectF(x, y, x + tileWidthL /2 * m, y + tileHeightL * m);

        LinkedList<TileObject> tiles = player.getHandTiles();
        int selectedTile = player.getSelectedTileIndex();
        for (int i = (position == PlayerPosition.SELF || position == PlayerPosition.LEFT?0:tiles.size() - 1); i < tiles.size() && i >= 0; i +=(position == PlayerPosition.SELF || position == PlayerPosition.LEFT?1:-1))
        {
            if (!tiles.get(i).isMoving())
            {
                Bitmap tile;
                switch (position) {
                    default : case SELF:
                        tile = tiles.get(i).getTileBitmap(TileOrientation.V_SELF);
                        break;
                    case RIGHT:
                        tile = TextureProvider.getGenericTileTexture(TileOrientation.V_RIGHT);
                        break;
                    case FRONT:
                        tile = TextureProvider.getGenericTileTexture(TileOrientation.V_FRONT);
                        break;
                    case LEFT:
                        tile = TextureProvider.getGenericTileTexture(TileOrientation.V_LEFT);
                        break;
                }

                canvas.drawBitmap(tile, new Rect(0, 0, tile.getWidth(), tile.getHeight()), new RectF(rect.left, rect.top - (i == selectedTile && playerSide == gameInstance.getCurrentPlayerTurn() ? tileHeightL/2 : 0), rect.right, rect.bottom - (i == selectedTile && playerSide == gameInstance.getCurrentPlayerTurn() ? tileHeightL/2 : 0)), paint);
            }

            if (position == PlayerPosition.SELF || position == PlayerPosition.FRONT)
            {
                rect.left += (tileWidthL * m * (i == tiles.size() - 2 && gameInstance.getCurrentPlayerTurn() == playerSide ? 1f: 1));
                rect.right += (tileWidthL * m * (i == tiles.size() - 2 && gameInstance.getCurrentPlayerTurn() == playerSide ? 1f: 1));
            }else
            {
                rect.top += (tileHeightL * 0.5f * m * (i == tiles.size() - 2 && gameInstance.getCurrentPlayerTurn() == playerSide ? 1f : 1));
                rect.bottom += (tileHeightL * 0.5f * m * (i == tiles.size() - 2 && gameInstance.getCurrentPlayerTurn() == playerSide ? 1f : 1));

            }
        }
    }

    private void newTurnAnimation(Canvas canvas) {
        PlayerObject p = gameInstance.getLocalPlayer();
        float posX = playerSelfPlacementX + (p.getHandTiles().size() - 1) * tileWidthL * PLAYER_SELF_SIZE_MODIFIER;
        float posY = playerSelfPlacementY;

        //TODO : Get the new Tile as a TileObject and set the goal to it. Might be relocated somewhere else in the code
        GameObjectAnimator a = new GameObjectAnimator(p.getHandTiles().getLast(), (int) posX, (int) posY);

        /*//Fake method, must put together GameObjects in a static class for the CIV to draw
        UIManager.addAnimatedObject(a);


        a.setGoal(a.getX(), a.getY() - (int)(tileHeightL/2), tileMovingSpeed);
        Bitmap b = p.getHand().getTiles().getLast().getTileBitmap(TileOrientation.V_SELF);
        if (b == null)
        {
            Tile t = p.getHand().getTiles().getLast();
            b = tileVSelf.copy(Bitmap.Config.ARGB_8888, true);
            Canvas tileCanvas = new Canvas(b);
            tileCanvas.drawBitmap(BitmapFactory.decodeResource(resources, t.getTextureId()), new Rect(0, 0, b.getWidth(), b.getHeight()), new RectF(0, 0, b.getWidth(), b.getHeight()), paint);
            t.setTileBitmap(b, TileOrientation.H_SELF);
        }

        a.setBitmap(b);
        a.setDims(tileWidthL* PLAYER_SELF_SIZE_MODIFIER, tileHeightL * PLAYER_SELF_SIZE_MODIFIER);*/
    }

    private void drawPlayerRiver(Canvas canvas, PlayerObject player){
        float x = 0;
        float y = 0;
        float m = PLAYER_OTHER_SIZE_MODIFIER;
        LinkedList<TileObject> tiles = player.getRiverTiles();
        tiles = arrangeRiver(tiles, player);
        //int delta = gameInstance.getPositionDelta(playerSide);
        PlayerPosition position = player.getPosition();
        for (int i = 0; i < tiles.size(); i++)
        {
            switch (position)
            {
				/*
				* 123456
				* 789012
				* 34567890
				*
				* last line works for p0, maybe not for p1, p2 and p3
				* */
                case SELF: x = playerSelfRiverPlacementX + (i < 6*3?i % 6:i - 6*2) * m * tileWidthL; y = playerSelfRiverPlacementY + Math.min(2, (i / 6)) * tileHeightL / 1.2f * m;break;	//Found a way to draw the rivers, need to apply to 1, 2, and 3
                case RIGHT: x = playerRightRiverPlacementX + (Math.min(2, i/6) * tileHeightL / 1.2f * m); y = playerRightRiverPlacementY - 0.2666f * m * tileWidthL + ((i < tiles.size()/6*6?-6 + i%6:-(tiles.size()%6) + i%6) + 1)* m * tileWidthL;break;
                case FRONT: x = playerFrontRiverPlacementX - ((i < tiles.size()%6)? i%6:(i - tiles.size()%6)%6) * tileWidthL*m ; y = playerFrontRiverPlacementY - (tiles.size() - i - 1)/6 * m * tileHeightL/1.2f;break;
                case LEFT: x = playerLeftRiverPlacementX - (i/6)*tileHeightL/1.2f*m; y = playerLeftRiverPlacementY + (i % 6) * tileWidthL * m;break;
            }

            RectF rect;
            if (position == PlayerPosition.SELF || position == PlayerPosition.FRONT) {
                rect = new RectF(x, y, x + tileWidthL * m, y + tileHeightL * m);
            }
            else {
                rect = new RectF(x, y, x + tileHeightL * m / 1.2f, y + tileWidthL * m * 1.2666f);
            }

            Bitmap tile;
            switch (position) {
                default : case SELF:
                    tile = tiles.get(i).getTileBitmap(TileOrientation.H_SELF);break;
                case RIGHT:
                    tile = tiles.get(i).getTileBitmap(TileOrientation.H_RIGHT);break;
                case FRONT:
                    tile = tiles.get(i).getTileBitmap(TileOrientation.H_FRONT);break;
                case LEFT:
                    tile = tiles.get(i).getTileBitmap(TileOrientation.H_LEFT);break;
            }

            Rect src = new Rect(0, 0, tile.getWidth(), tile.getHeight());
            canvas.drawBitmap(tile, src, rect, paint);
        }
    }

    private void drawPlayerMelds(Canvas canvas, PlayerObject player)
    {
        WindOrientation playerSide = player.getWind();
        //Side localWind = MultiplayerManager.getLocalPlayer().getPlayer().getWind();
        //int delta = playerSide.getValue() - localWind.getValue();
        PlayerPosition position = player.getPosition();
        float m = (player == gameInstance.getLocalPlayer() ? 1f : 0.8f);
        //LinkedList<TileSet> melds = player.getMelds();
        LinkedList<LinkedList<TileObject>> melds = player.getMelds();
        for (int s = 0; s < melds.size(); s++)
        {
            LinkedList<TileObject> meld = melds.get(s);
            int currentXAdd = 0;
            int currentYAdd = 0;
            for (int i = meld.size() - 1; i >= 0; i--) {
                TileOrientation orientation = meld.get(i).getOrientation();
                if (orientation != TileOrientation.H_LEFT && orientation != TileOrientation.H_FRONT && orientation != TileOrientation.H_RIGHT ) {
                    switch (position){
                        default : case SELF: orientation = TileOrientation.H_SELF; break;
                        case RIGHT: orientation = TileOrientation.H_RIGHT; break;
                        case FRONT: orientation = TileOrientation.H_FRONT; break;
                        case LEFT: orientation = TileOrientation.H_LEFT; break;
                    }
                }

                Bitmap tile = meld.get(i).getTileBitmap(orientation);
                RectF rect;

                //TODO : Something is missing here
                switch (position) {
                    default:case SELF: rect = new RectF(playerSelfMeldsPlacementX - s*(2*tileWidthL + tileHeightL) - currentXAdd - (orientation == TileOrientation.H_SELF?tileWidthL:tileHeightL/1.2f),
                            playerSelfMeldsPlacementY,
                            playerSelfMeldsPlacementX - s*(2*tileWidthL + tileHeightL) - currentXAdd ,
                            playerSelfMeldsPlacementY + (orientation == TileOrientation.H_SELF?tileHeightL:tileWidthL*1.2666f));
                        currentXAdd +=(orientation == TileOrientation.H_SELF?tileWidthL:tileHeightL/1.2f);
                        break;
                }

                canvas.drawBitmap(tile, new Rect(0, 0, tile.getWidth(), tile.getHeight()), rect, paint);

            }
        }
    }

    private LinkedList<TileObject> arrangeRiver(LinkedList<TileObject> tiles, PlayerObject player) {
        LinkedList<TileObject> result = new LinkedList<>();
        int currentIndex = 0;
        //int delta = MultiplayerManager.getPositionDelta(playerSide);
        PlayerPosition position = player.getPosition();
        switch (position)
        {
            case SELF:case LEFT: return tiles;
            case RIGHT: {
                while (result.size() < tiles.size())
                {
                    for (int i = Math.min(tiles.size() - 1, currentIndex + 5); i >= currentIndex; i--)
                        result.add(tiles.get(i));

                    currentIndex += 6;
                }
            }break;
            case FRONT: {
                while (result.size() < tiles.size())
                {
                    currentIndex = tiles.size() - tiles.size() % 6;

                    while (tiles.size() != result.size())
                    {
                        for(int i = currentIndex; i < Math.min(currentIndex + 6, tiles.size()); i++)
                            result.add(tiles.get(i));

                        currentIndex -= 6;
                    }
                }
            }break;

        }
        return result;
    }

    private void drawDeadWallL(Canvas canvas) {
        LinkedList<TileObject> tiles = gameInstance.getDeadWallTiles();
        int index = 0;
        float step = (tileWidthL * PLAYER_OTHER_SIZE_MODIFIER);
        RectF rect = new RectF(deadWallPlacementX,
                deadWallPlacementY,
                deadWallPlacementX + step,
                deadWallPlacementY+ (tileHeightL * PLAYER_OTHER_SIZE_MODIFIER));
        for (int i = 0; i < 5; i++) {
            Bitmap tex = TextureProvider.getGenericTileTexture(TileOrientation.R_SELF);
            canvas.drawBitmap(tex, new Rect(0, 0, tex.getWidth(), tex.getHeight()), new RectF(rect.left, rect.top + tileHeightL * PLAYER_OTHER_SIZE_MODIFIER / 5, rect.right, rect.bottom + tileHeightL * PLAYER_OTHER_SIZE_MODIFIER/ 5), paint);
            if (tiles.get(index + i*2).isHidden())
                canvas.drawBitmap(TextureProvider.getGenericTileTexture(TileOrientation.R_SELF), new Rect(0, 0, tex.getWidth(), tex.getHeight()), rect, paint);
            else{
                //canvas.drawBitmap(tileHSelf, new Rect(0, 0, tex.getWidth(), tex.getHeight()), rect, paint);
                canvas.drawBitmap(tiles.get(i*2).getTileBitmap(TileOrientation.H_SELF), new Rect(0, 0, tex.getWidth(), tex.getHeight()), rect, paint);
                //canvas.drawBitmap(covers[i], new Rect(0, 0, tileVFront.getWidth(), tileVFront.getHeight()), new RectF(rect.left, rect.top - tileHeightL * PLAYER_OTHER_SIZE_MODIFIER / 5/2, rect.right, rect.bottom - tileHeightL * PLAYER_OTHER_SIZE_MODIFIER/ 5/2), paint);
            }

            rect.left += step;
            rect.right += step;
        }
    }

}