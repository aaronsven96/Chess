package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGdxGame;

import server.ThreadServer;

public class ConnectScreen implements Screen {
    Label label;
    Stage stage;
    ThreadServer tcpData;
    private MyGdxGame game;
    public ConnectScreen(MyGdxGame game, ThreadServer tcpData){
        this.game=game;
        this.tcpData=tcpData;
    }
    @Override
    public void show() {
        System.out.println("Connect");
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        label = new Label("Waiting", game.skin);
        TextButton cancelButton = new TextButton("Cancel",game.skin);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(tcpData.socket);
                tcpData.socket.dispose();
                tcpData.thread.interrupt();
                game.setScreen(new MainScreen(game));
            }
        });
        table.add(label);
        table.row();
        table.add(cancelButton);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        if (tcpData.thread.getState()==Thread.State.TERMINATED){
            game.setScreen(new MainScreen(game));
            this.dispose();
        }
        Gdx.gl.glClearColor(1, 2/3, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
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
