package com.wizered67.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.wizered67.game.Screens.GameScreen;
import com.wizered67.game.Screens.LoadingScreen;

import java.util.HashMap;
import java.util.Map;

public class MainGame extends Game {
	GameScreen gameScreen;
	AssetManager assetManager;
    Map<String, Map<String, Animation>> loadedAnimations;
    MusicManager musicManager;
	@Override
	public void create() {
		Gdx.app.log("TEST", "TEST");
		assetManager = new AssetManager();
        musicManager = new MusicManager();
        loadedAnimations = new HashMap<String, Map<String, Animation>>();
		GameManager.init(this);
		gameScreen = new GameScreen();
        setScreen(new LoadingScreen(assetManager, gameScreen));
	}
	
}
