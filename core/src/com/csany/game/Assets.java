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

    public static final AssetDescriptor<Texture> CR_TEXTURE
            = new AssetDescriptor<Texture>("a.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> BLUE_PARTICLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("kek.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> LOADING_ATLAS
            = new AssetDescriptor<TextureAtlas>("loading.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> RED_PARTICLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("piros.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> YELLOW_PARTICLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("sarga.atlas", TextureAtlas.class);

    public static final AssetDescriptor<Texture> WINDOW
            = new AssetDescriptor<Texture>("window.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> BUTTON_ATLAS
            = new AssetDescriptor<TextureAtlas>("button.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> KEK_ATLAS
            = new AssetDescriptor<TextureAtlas>("kek.atlas", TextureAtlas.class);

    public static final AssetDescriptor<Music> PLAYING_MUSIC
            = new AssetDescriptor<Music>("sounds/Beat Loop 120.wav", Music.class);

    public static final AssetDescriptor<Music> MENU_MUSIC
            = new AssetDescriptor<Music>("sounds/Space Travel.mp3", Music.class);

    public static void prepare() {

        manager = new AssetManager();
        Texture.setAssetManager(manager);

    }

    public static void load() {
        manager.load(Assets.CR_TEXTURE);
        manager.load(Assets.BLUE_PARTICLE_ATLAS);
        manager.load(Assets.RED_PARTICLE_ATLAS);
        manager.load(Assets.YELLOW_PARTICLE_ATLAS);
        manager.load(Assets.WINDOW);
        manager.load(Assets.BUTTON_ATLAS);
        manager.load(Assets.PLAYING_MUSIC);
        manager.load(Assets.MENU_MUSIC);
    }

    public static void afterLoaded() {
        manager.get(PLAYING_MUSIC).setLooping(true);
        manager.get(MENU_MUSIC).setLooping(true);
    }

    public static void unload() {
        manager.dispose();
    }

}
