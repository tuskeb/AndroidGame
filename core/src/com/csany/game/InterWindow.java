package com.csany.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class InterWindow extends MyWindow {
    public InterWindow() {
        setTitle("Játék megállítva!");

        MyButton button;


        TextButton textButtonNext = new MyButton("Újra", MyWindow.textButtonStyle);
        textButtonNext.setSize(180, 60);
        textButtonNext.setPosition(getWidth() / 2 - 90, 30);


        addActor(textButtonNext);

        button = new MyButton("Folytatás", textButtonStyle);
        button.setSize(210, 60);
        button.setPosition(30, 110);

        addActor(button);


        button = new MyButton("Kilépés", textButtonStyle);
        button.setSize(210, 60);
        button.setPosition(360, 110);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        addActor(button);

    }
}
