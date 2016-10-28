package com.wizered67.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.wizered67.game.GameManager;

import java.util.HashMap;

/**
 * Created by Adam on 10/26/2016.
 */
public class LoadingScreen implements Screen {
    private AssetManager assetManager;
    private Screen nextScreen;

    public LoadingScreen(AssetManager am, Screen ns) {
        assetManager = am;
        nextScreen = ns;
        loadDefault();
    }

    public void loadDefault() {
        assetManager.load("Sounds/talksoundmale.wav", Sound.class);
        assetManager.load("Sounds/talksoundfemale.wav", Sound.class);
        assetManager.load("Sounds/sfxblipmale.wav", Sound.class);
        assetManager.load("Sounds/intense.wav", Sound.class);
        assetManager.load("Music/crossexamination.mp3", Music.class);

        assetManager.load("Animations/Edgeworth.pack", TextureAtlas.class);
    }

    public void loadAnimations() {
        TextureAtlas atlas = assetManager.get("Animations/Edgeworth.pack", TextureAtlas.class);
        Array<TextureAtlas.AtlasRegion> think = atlas.findRegions("Edgeworth");
        Array<TextureAtlas.AtlasRegion> idle = atlas.findRegions("Static");
        Array<TextureAtlas.AtlasRegion> confront = atlas.findRegions("Confront");
        Array<TextureAtlas.AtlasRegion> point = atlas.findRegions("Point");
        Animation thinkAnim = new Animation(0.3f, think);
        Animation idleAnim = new Animation(1, idle);
        Animation confrontAnim = new Animation(1, confront);
        Animation pointAnim = new Animation(1, point);
        HashMap<String, Animation> edgeworthAnimations = new HashMap<String, Animation>();
        edgeworthAnimations.put("Think", thinkAnim);
        edgeworthAnimations.put("Idle", idleAnim);
        edgeworthAnimations.put("Confront", confrontAnim);
        edgeworthAnimations.put("Point", pointAnim);
        GameManager.loadedAnimations().put("Edgeworth", edgeworthAnimations);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (assetManager.update()) {
            loadAnimations();
            GameManager.game.setScreen(nextScreen);
        }
        System.out.println(assetManager.getProgress());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
