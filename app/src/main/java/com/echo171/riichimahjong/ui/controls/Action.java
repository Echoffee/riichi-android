package com.echo171.riichimahjong.ui.controls;

import com.echo171.riichimahjong.ui.GameInstance;
import com.echo171.riichimahjong.ui.gameobjects.PlayerObject;

/**
 * Created by echo on 20/06/2017.
 */
public interface Action {
    void execute(GameInstance game, PlayerObject caller);
}