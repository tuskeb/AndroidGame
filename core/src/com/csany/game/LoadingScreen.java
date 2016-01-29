package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

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
        sprite.setPosition(MyScreen.WORLD_WIDTH/2-sprite.getWidth()/2,MyScreen.WORLD_HEIGHT/2-sprite.getHeight()/2);
        camera = new OrthographicCamera(1024,768);
        camera.translate(512,384);

        Assets.load();

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (Assets.manager.update()) {
            Assets.afterLoaded();
            ((MyGame) Gdx.app.getApplicationListener())
                    .setScreen(new MenuScreen());
        }

        batch.begin();

        final int i = (int) (((float)loadingAtlasRegions.size * Assets.manager.getProgress()) - 1);
        sprite.setRegion(loadingAtlasRegions.get(Math.max(0, i)));

        sprite.draw(batch);

        batch.end();

        Assets.load();
    }

    @Override
    public void hide() {
        Assets.manager.unload(Assets.LOADING_ATLAS.fileName);
    }
}


