package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import Server.myHost;
import Server.Client;


import java.awt.Button;
import java.awt.Label;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Label label;
	Stage stage;
	ConcurrentLinkedQueue<String> data;
	@Override
	public void create () {
		data=new ConcurrentLinkedQueue<String>();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		label = new Label();
		com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
		style.font= new BitmapFont();
		TextButton button = new TextButton("Host",style);
		try {
			System.out.println(InetAddress.getLocalHost().toString().split("/")[1]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		TextButton button2 = new TextButton("Client",style);
		System.out.println("Host");
		button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				myHost host = new myHost();
				host.makeSocket();
				System.out.println("Button Pressed");

			}
		});
		button2.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				Client client = new Client();
				client.ConnectToSocket(data);
				System.out.println("Button Pressed");
			}
		});
		table.add(button);
		table.row();
		table.add(button2);

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 2/3, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
