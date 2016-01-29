package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleActor extends Actor {


    ShapeRenderer renderer = new ShapeRenderer();

    ParticleActor() {
        setX((float)(Math.random() * Gdx.graphics.getWidth()));
        setY((float)(Math.random() * Gdx.graphics.getHeight()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        renderer.begin(ShapeRenderer.ShapeType.Filled);


        renderer.circle(getX(), getY(), 3);

        renderer.end();

    }
}
