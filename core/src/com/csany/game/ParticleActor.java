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

    ShapeRenderer renderer = new ShapeRenderer();
    float stateTime =0;
    Sprite sprite;
    Animation animation;

    final Array<TextureAtlas.AtlasRegion> textureAtlasRegions_BLUE = Assets.manager.get(Assets.BLUE_PARTICLE_ATLAS).getRegions();
    final Array<TextureAtlas.AtlasRegion> textureAtlasRegions_RED = Assets.manager.get(Assets.RED_PARTICLE_ATLAS).getRegions();


    final boolean state;
    boolean finalState;



    float rotation = (float)(Math.random() * Math.PI);
    float speed = .5f;

    ParticleActor(boolean bla, float div) {
        this.state = bla;

        if(this.state) {
            setX((float)(Math.random() * div));
        } else {
            setX(div + (float)(Math.random() * (Gdx.graphics.getWidth() - div)));
        }

        setY(Gdx.graphics.getHeight() + 10 + (float)(Math.random() * 10) - 5);

        if(bla) kekRainy();
        else pirosRainy();

    }

    private void kekRainy(){ //Ha nem tetszik, akkor Refactor
        sprite = new Sprite(textureAtlasRegions_BLUE.first());
        sprite.setRegion(textureAtlasRegions_BLUE.first());
        animation = new Animation(1 / 30f, textureAtlasRegions_BLUE, Animation.PlayMode.LOOP);
        setSize(0.5f, 0.5f);
        setTouchable(Touchable.disabled);
    }

    private  void pirosRainy(){
        sprite = new Sprite(textureAtlasRegions_RED.first());
        sprite.setRegion(textureAtlasRegions_RED.first());
        animation = new Animation(1 / 30f, textureAtlasRegions_RED, Animation.PlayMode.LOOP);
        setSize(0.5f, 0.5f);
        setTouchable(Touchable.disabled);
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
            setY(getY() + (float) Math.sin(rotation) * speed);
        } else {
            setX(getX() + (finalState ? -MOVING : MOVING));
        }
/* stateTime += delta;

		sprite.setRegion(animation.getKeyFrame(stateTime));

		if(!hasCollected) super.act(delta);
		else {
			deactivate();
			final float oldWidth = getWidth();
			setSize(getWidth() * 1.05f, getHeight() * 1.05f);
			setPosition(getX() - (getWidth() - oldWidth) / 2, getY() - (getWidth() - oldWidth) / 2);
            final float alpha = sprite.getColor().a*0.95f;
            if(alpha < 0.1f) {
                delete();
            }

			sprite.setAlpha(alpha);
			//sprite.setColor(0,0,0,sprite.getColor().a*0.95f);

			//setVisible(false);
		}*/
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

    }
}
