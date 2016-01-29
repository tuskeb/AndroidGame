package com.csany.game;

public class GameScreen extends MyScreen {

    GameStage gameStage;

    GameScreen() {

        super();

gameStage = new GameStage(viewport, batch);


    }

}
