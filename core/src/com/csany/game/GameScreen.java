package com.csany.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends MyScreen {

    GameStage gameStage;
    ControlStage controlStage;
    InterWindow interWindow;

    GameScreen() {
        super();

        setBackgroundColor(1, 1, 1);


        gameStage = new GameStage(viewport, batch);

        controlStage = new ControlStage(viewport, gameStage);

    }


    public void onStateChange() {
        //System.out.println("state changed: " + gameStage.getState());
        if (interWindow != null) {
            interWindow.remove();
            interWindow = null;
        }

        if (gameStage.INTERBOOLEAN) {
            Gdx.input.setInputProcessor(gameStage);
            return;
        }
        interWindow = new InterWindow();
        controlStage.addActor(interWindow);

        Gdx.input.setInputProcessor(controlStage);


    }

    @Override
    public void render(float delta) {
        super.render(delta);

        gameStage.act(delta);
        gameStage.draw();

        controlStage.act(delta);
        controlStage.draw();

    }
}
