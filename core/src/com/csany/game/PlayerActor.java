package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class PlayerActor extends Actor {
    final Array<TextureAtlas.AtlasRegion> textureAtlasRegions_YELLOW = Assets.manager.get(Assets.YELLOW_PARTICLE_ATLAS).getRegions();

    Sprite sprite;
    Animation animation;

    class Segment {
        float x, y;
        float px, py;
        public Segment(float x) {
            this.x = x;
            this.y = getY();
            this.py = getY() - speed;
            this.px = getX();
        }
    }

    ArrayList<Segment> segments = new ArrayList<Segment>();

    ShapeRenderer renderer = new ShapeRenderer();

    float movement = 0;
    float speed = 5f;
    int i = 0;

    private float stateTime = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;

        sprite.setRegion(animation.getKeyFrame(stateTime));

        switch (Gdx.app.getType()) {
            case Android:
                movement = Gdx.input.getAccelerometerY() * 3;
                break;
            case Desktop:
                movement = (Gdx.input.getX() - getX()) / 10;
                break;
        }


        float newX = getX() + movement;
        if(++i == 2) {
            i = 0;
            segments.add(new Segment(newX));
        }

        if(newX < 0) {
            newX = 0;
        } else if (newX > MyScreen.WORLD_WIDTH) {
            newX = MyScreen.WORLD_WIDTH;
        }
        setX(newX);

    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(), getY());
    }

    PlayerActor() {

        sprite = new Sprite(textureAtlasRegions_YELLOW.first());
        sprite.setRegion(textureAtlasRegions_YELLOW.first());
        animation = new Animation(1 / 30f, textureAtlasRegions_YELLOW, Animation.PlayMode.LOOP);
        setSize(32, 32);

        setX(MyScreen.WORLD_WIDTH / 2);
        setY(150);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(0, 0, 0, 1);

        sprite.draw(batch);

        renderer.circle(getX(), getY(), 5);

        for(Segment segment : segments) {
            renderer.rectLine(segment.px, segment.py, segment.x, segment.y, 3);
        }

        renderer.end();

    }


}
