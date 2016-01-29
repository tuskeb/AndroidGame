package com.csany.game;

public class GameScreen extends MyScreen {

    GameStage gameStage;
    ControlStage controlStage;

    GameScreen() {
        super();

        setBackgroundColor(1,1,1);

        gameStage = new GameStage(viewport, batch);
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
