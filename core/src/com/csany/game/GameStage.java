package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage implements GestureDetector.GestureListener  {



    PlayerActor playerActor = new PlayerActor();

    float h = 0;

    public GameStage(Viewport viewport, Batch batch) {
        super(viewport, batch);

        addActor(playerActor);

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        h += playerActor.speed;

        for(Actor actor : getActors()) {
           // if(actor instanceof ParticleActor) {
                //ParticleActor pa = (ParticleActor)actor;
            actor.setY(actor.getY() - playerActor.speed);
            if(actor.getY() < 0) {
                actor.remove();
            }
            //}
        }

        if(h > 50) {
            h = 0;
            for (int i = 0;i<5;i++) {

            addActor(new ParticleActor());
            }

        }

    }


    @Override
    public void draw() {
        super.draw();



    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
