package com.csany.game;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

    public static AssetManager manager;

    /* PÉLDÁK


	public static final AssetDescriptor<TextureAtlas> LOADING_ATLAS
			= new AssetDescriptor<TextureAtlas>("loading.atlas", TextureAtlas.class);

	public static final AssetDescriptor<Texture> MENU_MOLE
			= new AssetDescriptor<Texture>("menumole.png", Texture.class);


    public static final AssetDescriptor<Music> MAZESELECTING_MUSIC
            = new AssetDescriptor<Music>("sounds/Loungekore.mp3", Music.class);


    public static final AssetDescriptor<Sound> BLACKHOLE_SOUND
            = new AssetDescriptor<Sound>("sounds/black_hole.wav", Sound.class);

     */

    public static final AssetDescriptor<TextureAtlas> BLUE_PARTICLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("kek.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> RED_PARTICLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("piros.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> YELLOW_PARTICLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("sarga.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> LOADING_ATLAS
            = new AssetDescriptor<TextureAtlas>("loading.atlas", TextureAtlas.class);


    public static void prepare() {

        manager = new AssetManager();
        Texture.setAssetManager(manager);

    }


    public static void load() {
        manager.load(Assets.BLUE_PARTICLE_ATLAS);
        manager.load(Assets.RED_PARTICLE_ATLAS);
        manager.load(Assets.YELLOW_PARTICLE_ATLAS);
        //manager.load(Assets.SOMETHING)<--This is the syntax
    }

    public static void afterLoaded() {

    }

    public static void unload() {
        manager.dispose();
    }

}
