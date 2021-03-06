package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ControlStage extends Stage {

    private final SpriteBatch batch;

    GameStage gameStage;

    BitmapFont font = MyScreen.FONT_HOBO_STD;

    public ControlStage(Viewport viewport, GameStage gameStage) {
        super(viewport, new SpriteBatch());
        batch = (SpriteBatch)getBatch();

        this.gameStage = gameStage;
    }

    @Override
    public void draw() {
        super.draw();

        batch.begin();
        font.draw(batch, String.format("%.1f%% %d", gameStage.getStat() * 100, gameStage.getBadParticles()), 10, MyScreen.WORLD_HEIGHT);
        batch.end();

    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

}
