package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

public class ParticleActor extends Actor {

    private ShapeRenderer renderer = new ShapeRenderer();
    private float stateTime =0;
    private  Sprite sprite;
    private  Animation animation;

    final Array<TextureAtlas.AtlasRegion> textureAtlasRegions;

    private final boolean state;
    private boolean finalState;

    private float rotation = (float)Math.PI + (float)(Math.random() * Math.PI);
    private float speed = .3f;

    ParticleActor(boolean bla, float div) {
        this.state = bla;

        textureAtlasRegions = bla ? Assets.manager.get(Assets.BLUE_PARTICLE_ATLAS).getRegions() : Assets.manager.get(Assets.RED_PARTICLE_ATLAS).getRegions();

        sprite = new Sprite(textureAtlasRegions.first());
        animation = new Animation(1 / 3f, textureAtlasRegions, Animation.PlayMode.LOOP);
        sprite.setSize(32, 32);

        if(this.state) {
            setX((float)(Math.random() * div));
        } else {
            setX(div + (float)(Math.random() * (MyScreen.WORLD_WIDTH - div)));
        }

        setY(MyScreen.WORLD_HEIGHT + (MyScreen.WORLD_HEIGHT / GameStage.ROWS) + (float) (Math.random() * MyScreen.WORLD_HEIGHT / GameStage.ROWS * 2) - (MyScreen.WORLD_HEIGHT / GameStage.ROWS));

    }



    private boolean freezed = false;

    public boolean isFreezed() {
        return freezed;
    }

    private float impulse;

    public void freeze(float div, float impulse) {
        freezed = true;
        finalState = getX() < div;
        this.impulse = Math.abs(impulse) * (finalState ? -1f : 1f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime));

        if(!freezed) {
            setX(getX() + (float)Math.cos(rotation) * speed);
            setY(getY() + (float)Math.sin(rotation) * speed);
        } else {
            setX(getX() + impulse / 7);
        }

    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(), getY());
    }

    public boolean isGood() {
        return state == finalState;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        sprite.draw(batch);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        if(this.state) renderer.setColor(0, 0, 1, 1);
        else renderer.setColor(1, 0, 0, 1);

        renderer.circle(getX() - 2, getY() - 2, 5);

        renderer.end();


    }
}
