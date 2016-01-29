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
    float div = Gdx.graphics.getWidth() / 2;
    float newDiv = 0;

    private int totalParticles = 0, goodParticles = 0;

    public float getStat() {
        if(totalParticles > 0) {
            return  goodParticles != 0 ? (float)goodParticles / (float)totalParticles : 0;
        } else {
            return 1;
        }
    }

    public GameStage(Viewport viewport, Batch batch) {
        super(viewport, batch);

        addActor(playerActor);
    }

    public void moveDivision() {

        div += (newDiv - div) / 10;

        if((int)div == (int)newDiv) {
            newDiv = (float)Math.random() * Gdx.graphics.getWidth();
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        moveDivision();

        h += playerActor.speed;

        for(Actor actor : getActors()) {
            if(actor instanceof ParticleActor) {
                ParticleActor pa = (ParticleActor)actor;
                pa.setY(actor.getY() - playerActor.speed);
                if(pa.getY() < playerActor.getY()) {
                    if(!pa.isFreezed()) {
                        totalParticles += 1;

                        pa.freeze(playerActor.getX());

                    }
                }
                if(pa.getY() < 0) {
                    if(pa.isGood()) {
                        goodParticles += 1;
                    }
                    pa.remove();
                }
            }
        }

        /*for (PlayerActor.Segment segment : playerActor.segments) {
            segment.y -= playerActor.speed;
            segment.x -= playerActor.speed;
            segment.px -= playerActor.speed;
            segment.py -= playerActor.speed;
        }*/

        if(h > 100) {
            h = 0;

            generateRow();

        }

    }

    public int getTotalParticles() {
        return totalParticles;
    }

    private void generateRow() {

        int a = 5;
        int b = 5;

        for (int i = a;--i > 0;) {
            ParticleActor pa = new ParticleActor(true, div);
            addActor(pa);
        }

        for (int i = b;--i > 0;) {
            ParticleActor pa = new ParticleActor(false, div);
            addActor(pa);
        }

    }



    public int getGoodParticles() {
        return goodParticles;
    }

    public int getBadParticles() {
        return totalParticles - goodParticles;
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
