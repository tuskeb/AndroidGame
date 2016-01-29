package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleActor extends Actor {

    ShapeRenderer renderer = new ShapeRenderer();

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

    }

    private boolean freezed = false;

    public boolean isFreezed() {
        return freezed;
    }

    public void freeze(float div) {
        freezed = true;
        finalState = getX() < div;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!freezed) {
        setX(getX() + (float)Math.cos(rotation) * speed);
            setY(getY() + (float) Math.sin(rotation) * speed);
        }

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

        renderer.circle(getX(), getY(), 3);

        renderer.end();

    }
}
