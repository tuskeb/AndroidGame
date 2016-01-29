package com.csany.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/** A játék menüje*/
public class MenuScreen extends MyScreen{
    Stage stage;
    private final Music music = Assets.manager.get(Assets.MENU_MUSIC);

    GameActor actor;

    Texture backgroundTexture = new Texture(Gdx.files.internal("background.png"));
    Sprite backgroundSprite =new Sprite(backgroundTexture);



    public MenuScreen() {
        super();
        setBackgroundColor(0.3f, 0.8f, 0.9f);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);



        stage = new Stage(viewport) {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.ESCAPE:
                    case Input.Keys.BACK:
                        System.exit(0);
                        break;
                }
                return false;
            }

        };



        MyButton button; Skin skin = new Skin();

        PlayerActor playerActor= new PlayerActor();

        //Label label;

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        final float ROW_HEIGHT = 75f;

        /*label = new Label("Endless Ball Running - The Game", MyScreen.LABEL_STYLE);
        label.setPosition(WORLD_HEIGHT / 2f, WORLD_WIDTH / 2f);
        table.add(label)
                .width(500f)
                .height(130f);
        table.row().height(ROW_HEIGHT);*/



        button = new MyButton("Indítás", MyScreen.TEXT_BUTTON_STYLE_LABEL);//MyButton(String text, Skin skin, String styleName) || (String text, TextButtonStyle style) || MyButton(String text, Skin skin)
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((MyGame) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });
        button.setPosition(WORLD_HEIGHT / 2f, WORLD_WIDTH / 2f);
        table.add(button);
        table.row().height(ROW_HEIGHT);

        button = new MyButton("Súgó", MyScreen.TEXT_BUTTON_STYLE_LABEL);//MyButton(String text, Skin skin, String styleName) || (String text, TextButtonStyle style) || MyButton(String text, Skin skin)
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((MyGame) Gdx.app.getApplicationListener()).setScreen(new HelpScreen());
            }
        });
        button.setPosition(WORLD_HEIGHT / 2f, WORLD_WIDTH / 2f);
        table.add(button);
        stage.addActor(table);
        //stage.addActor(playerActor);

    }

    public void renderBackground() {
        backgroundSprite.draw(batch);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
        camera.update();
       /* camera.viewportWidth = width;
        camera.viewportHeight = height;

        stage.getViewport().update(width, height);*/
        /*camera = new OrthographicCamera(MyScreen.WORLD_WIDTH,MyScreen.WORLD_HEIGHT);
        camera.translate(MyScreen.WORLD_WIDTH/2,MyScreen.WORLD_HEIGHT/2);
        viewport = new ExtendViewport(MyScreen.WORLD_WIDTH,MyScreen.WORLD_HEIGHT, camera);
        stage.setViewport(viewport);
        stage.getCamera().update();
        */
    }


    @Override
    public void hide() {
        super.hide();
        music.pause();
    }

    @Override
    public void show() {
        music.play();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        renderBackground();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        stage.act(delta);
        stage.draw();
        batch.end();
    }

}
