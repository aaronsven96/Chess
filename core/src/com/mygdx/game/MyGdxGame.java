package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screens.MainScreen;



public class MyGdxGame extends Game {
	public SpriteBatch gameBatch;
	public BitmapFont gameFont;
	public Skin skin;

	@Override
	public void create () {
		skin = new Skin(new FileHandle("skin/uiskin.json"));
		gameFont = new BitmapFont();
		gameBatch = new SpriteBatch();
		this.setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
	}
}
