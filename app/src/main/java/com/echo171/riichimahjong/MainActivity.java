package com.echo171.riichimahjong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.echo171.riichimahjong.game_ui.GameCreator;
import com.echo171.riichimahjong.game_ui.TextureProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextureProvider.initialize(getResources());
        GameCreator.createDemoGame();
        setContentView(R.layout.activity_main);
        CImageView civ = (CImageView) findViewById(R.id.civ);
        civ.setDrawer(new Drawer(getResources()));
    }
}