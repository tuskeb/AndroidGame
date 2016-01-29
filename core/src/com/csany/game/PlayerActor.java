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
        final float x, y;
final float px, py;
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
            {
                //angle = (float)Math.atan2(Gdx.input.getY() - y,  Gdx.input.getX() - x);
                if(Gdx.input.getX() > x)
                angle += .01;
                else angle -= .01;

            }
        }

        segments.add(new Segment(x, y));

        prevX = x;
        prevY = y;



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(1, 1, 0, 1);

        for(Segment segment : segments) {
            renderer.rectLine(segment.px, segment.py, segment.x, segment.y, 3);

        }

        renderer.end();

    }


}
