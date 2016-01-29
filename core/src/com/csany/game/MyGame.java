package com.csany.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**MAIN CLASS*/

public class MyGame extends Game implements ApplicationListener {
	SpriteBatch batch;

	public Preferences prefs;

	@Override
	public void create () {
		batch = new SpriteBatch();

		prefs = Gdx.app.getPreferences("MyGame");

		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);

		Assets.prepare();

		setScreen(new LoadingScreen());

	}


	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}


	@Override
	public void dispose() {
		super.dispose();
		Assets.unload();
	}

}
