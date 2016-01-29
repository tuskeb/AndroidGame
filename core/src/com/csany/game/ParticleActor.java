package com.csany.game;

import com.badlogic.gdx.Gdx;
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


    final boolean state;
    boolean finalState;



    float rotation = (float)(Math.random() * Math.PI);
    float speed = .5f;

    ParticleActor(boolean bla, float div) {
        this.state = bla;

        textureAtlasRegions = bla ? Assets.manager.get(Assets.BLUE_PARTICLE_ATLAS).getRegions() : Assets.manager.get(Assets.RED_PARTICLE_ATLAS).getRegions();;

        sprite = new Sprite(textureAtlasRegions.first());
        animation = new Animation(1 / 30f, textureAtlasRegions, Animation.PlayMode.LOOP);

        if(this.state) {
            setX((float)(Math.random() * div));
        } else {
            setX(div + (float)(Math.random() * (Gdx.graphics.getWidth() - div)));
        }

        setY(Gdx.graphics.getHeight() + 10 + (float) (Math.random() * 10) - 5);

    }



    private boolean freezed = false;

    public boolean isFreezed() {
        return freezed;
    }

    public void freeze(float div) {
        freezed = true;
        finalState = getX() < div;
    }

    private final static float MOVING = 2;

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!freezed) {
            stateTime += delta;
            sprite.setRegion(animation.getKeyFrame(stateTime));
            setX(getX() + (float)Math.cos(rotation) * speed);
            setY(getY() + (float)Math.sin(rotation) * speed);
        } else {
            setX(getX() + (finalState ? -MOVING : MOVING));
        }

    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(30, 30);
    }

    public boolean isGood() {
        return state == finalState;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        renderer.begin(ShapeRenderer.ShapeType.Filled);

        if(this.state) renderer.setColor(0, 0, 1, 1);
        else renderer.setColor(1, 0, 0, 1);

        renderer.circle(getX() - 2, getY() - 2, 4);

        renderer.end();

        sprite.draw(batch);


    }
}
