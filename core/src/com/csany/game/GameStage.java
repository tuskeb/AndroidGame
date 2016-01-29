package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage implements GestureDetector.GestureListener  {

    private PlayerActor playerActor = new PlayerActor();

    private float nextRow = 0;
    private float div = MyScreen.WORLD_WIDTH / 2;
    private float newDiv = 0;

    private int totalParticles = 0, goodParticles = 0;

    public float getStat() {
        if(totalParticles > 0) {
            return  goodParticles != 0 ? (float)goodParticles / (float)totalParticles : 0;
        } else {
            return 1;
        }
    }


    private final Texture backgroundTexture;

    {
        backgroundTexture = new Texture(new Pixmap(Gdx.files.internal("mdn_sq.png")));
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public GameStage(Viewport viewport, Batch batch) {
        super(viewport, batch);

        addActor(playerActor);


    }

    public void moveDivision() {

        div += (newDiv - div) / 8;

        if((int)div == (int)newDiv) {
            newDiv = (float)Math.random() * MyScreen.WORLD_WIDTH;
        }

    }

    SpriteBatch spriteBatch = new SpriteBatch();

    @Override
    public void act(float delta) {
        super.act(delta);

        moveDivision();


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

        for (PlayerActor.Segment segment : playerActor.segments) {
            segment.y -= playerActor.speed;
            segment.py -= playerActor.speed;
        }

        while(playerActor.segments.size() > 0) {
            PlayerActor.Segment last = playerActor.segments.get(0);

            if(last.y < 0) {
                playerActor.segments.remove(0);
            } else break;
        }


        nextRow -= playerActor.speed;
        if(nextRow < 0) {
            nextRow = MyScreen.WORLD_HEIGHT / 5;

            generateRow();

        }

    }

    public int getTotalParticles() {
        return totalParticles;
    }

    private void generateRow() {

        final int count = (int)(playerActor.speed * 1.1f);

        ParticleActor pa;

        for (int i = count;--i > 0;) {

            pa = new ParticleActor(true, div);
            addActor(pa);

            pa = new ParticleActor(false, div);
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
        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, 0, 0, MyScreen.WORLD_WIDTH, MyScreen.WORLD_HEIGHT, 0, 0, 60, 60);
        spriteBatch.end();
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
