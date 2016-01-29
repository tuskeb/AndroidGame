package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PlayerActor extends Actor {

    /*
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
*/
    ShapeRenderer renderer = new ShapeRenderer();

    float movement = 0;
    float speed = 5f;

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (Gdx.app.getType()) {
            case Android:
                movement = Gdx.input.getAccelerometerY() * 3;
                break;
            case Desktop:
                movement = (Gdx.input.getX() - getX()) / 10;
                break;
        }

        //segments.add(new Segment(x, y));

        float newX = getX() + movement;
        if(newX < 0) {
            newX = 0;
        } else if (newX > Gdx.graphics.getWidth()) {
            newX = Gdx.graphics.getWidth();
        }
        setX(newX);

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

        /*
        for(Segment segment : segments) {
            renderer.rectLine(segment.px, segment.py, segment.x, segment.y, 3);

        }
*/
        renderer.end();

    }


}
