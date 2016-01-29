package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javafx.scene.Camera;

public class LoadingScreen extends MyScreen {

    Stage stage;

    Array<TextureAtlas.AtlasRegion> loadingAtlasRegions;
    public LoadingScreen() {
        setBackgroundColor(0f, 0f, 0f);

    }
    Sprite sprite = new Sprite();


    @Override
    public void show() {
        Assets.manager.load(Assets.LOADING_ATLAS);
        Assets.manager.finishLoading();

        loadingAtlasRegions = Assets.manager.get(Assets.LOADING_ATLAS).getRegions();
        sprite.setSize(loadingAtlasRegions.get(0).getRegionWidth(), loadingAtlasRegions.get(0).getRegionHeight());
        sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth() / 2, Gdx.graphics.getHeight()/2-sprite.getHeight()/2);

        Assets.load();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        sprite.setSize(loadingAtlasRegions.get(0).getRegionWidth(), loadingAtlasRegions.get(0).getRegionHeight());
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2, Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (Assets.manager.update()) {
            Assets.afterLoaded();
            ((MyGame) Gdx.app.getApplicationListener())
                    .setScreen(new GameScreen());
        }

        batch.begin();

        int i = (int) (((float)loadingAtlasRegions.size * Assets.manager.getProgress()) - 1);
        sprite.setRegion(loadingAtlasRegions.get(Math.min(Math.max(0, i), loadingAtlasRegions.size - 1)));
        sprite.draw(batch);

        batch.end();

    }

    @Override
    public void hide() {
        Assets.manager.unload(Assets.LOADING_ATLAS.fileName);

    }

}
