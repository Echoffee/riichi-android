package com.echo171.riichimahjong.game_ui.controls;

import com.echo171.riichimahjong.game_ui.GameInstance;
import com.echo171.riichimahjong.game_ui.gameobjects.PlayerObject;

/**
 * Created by echo on 20/06/2017.
 */
public interface Action {
    void execute(GameInstance game, PlayerObject caller);
}