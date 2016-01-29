package com.csany.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends MyScreen {

    GameStage gameStage;
    ControlStage controlStage;
    InterWindow interWindow;

    GameScreen() {
        super();

        setBackgroundColor(1, 1, 1);


        gameStage = new GameStage(viewport, batch){
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.ESCAPE:
                    case Input.Keys.BACK:
                        interWindow = new InterWindow(true);
                        break;
                }
                return false;
            }

        };
        controlStage = new ControlStage(viewport, gameStage);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        gameStage.act(delta);
       // batch.begin();
        gameStage.draw();

       // batch.end();

        controlStage.act(delta);
        controlStage.draw();


    }
}
