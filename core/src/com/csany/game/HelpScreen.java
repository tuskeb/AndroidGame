package com.csany.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class HelpScreen extends MyScreen{
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Table table;
    private Label TABLE_NEVERMIND, TABLE_NEVERMIND2,  TABLE_SZOVEG1, TABLE_SZOVEG2;
    private TextButton button;
    static int PUDING = 30;
    Texture backGroundImage = new Texture(Gdx.files.internal("background.png"));

    public HelpScreen(){
        super();
        batch=new SpriteBatch();

        stage = new Stage() {
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

        TABLE_SZOVEG1 = new Label("A játékmenet során a játékos bizonyos reakcióidejére építve halad előre a játék.\n" +
                "A karaktert a kijelző oldal-irányba történő döntésével lehet irányítani.\n" +
                "A játék célja a <piros> és <kék> karakterek egymástól történő szétválasztása,\n" +
                "amit adott idő alatt kell megfelelő százalékos arányban végrehajtani.\n" +
                "Rossz oldalnak számít az, amikor például a jobbról érkező karakterek,\n" +
                " a bal oldalon érkeznek meg, és természetesen ugyanez visszafelé is igaz.\n" +
                "A százalékérték melletti szám, azokat a karaktereknek a darabszámát jelöli, amik rossz helyre kerültek.", MyScreen.LABEL_STYLE2);
        TABLE_SZOVEG1.setAlignment(Align.center);




        table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.setFillParent(true);
        stage.addActor(table);

        Label label1 = new Label("S Ú G Ó",MyScreen.LABEL_STYLE_TOP);
        label1.setAlignment(Align.top, Align.center);
        table.add(label1).width(500f).height(130f);
        table.row().pad(PUDING);
        table.add(TABLE_SZOVEG1);
        table.row();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backGroundImage, 0, 0);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }

    /* Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(image, 250, 200);
        batch.draw(backGroundImage, 0, 0);*/

}
