package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PlayerActor extends Actor {

    class Segment {
        float x, y;
        float px, py;
        public Segment(float x, float y) {
            this.x = x;
            this.y = y;
            this.py = prevY;
            this.px = prevX;
        }
    }

    ArrayList<Segment> segments = new ArrayList<Segment>();

    ShapeRenderer renderer = new ShapeRenderer();

    float prevX = 100, prevY = 30;
    float angle = (float)Math.PI / 2;
    float speed = 5f;

    @Override
    public void act(float delta) {
        super.act(delta);

        float x = prevX + (float)Math.cos(angle) * speed;
        float y = prevY + (float)Math.sin(angle) * speed;

        switch (Gdx.app.getType()) {
            case Android:
                //world.setGravity(new Vector2(Gdx.input.getAccelerometerY(), -Gdx.input.getAccelerometerX()));
                break;
            case Desktop:
                angle = (float)Math.atan2(Gdx.input.getY() - getY(),  Gdx.input.getX() - getX());
                break;
        }

        //segments.add(new Segment(x, y));

        setX(getX() + (float)Math.cos(angle) * speed);

        prevX = x;
        prevY = y;



    }

    PlayerActor() {
        setX(Gdx.graphics.getWidth() / 2);
        setY(150);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(0, 0, 0, 1);

        renderer.circle(getX(), getY(), 3);

        for(Segment segment : segments) {
            renderer.rectLine(segment.px, segment.py, segment.x, segment.y, 3);

        }

        renderer.end();

    }


}
