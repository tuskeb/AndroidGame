package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen extends MyScreen {

    GameStage gameStage;

    GameScreen() {

        super();

        gameStage = new GameStage(viewport, batch);


    }

    @Override
    public void render(float delta) {
        super.render(delta);

        gameStage.act(delta);
        gameStage.draw();

    }
}
