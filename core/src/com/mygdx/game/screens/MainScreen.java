package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGdxGame;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;

import server.TCPCommunication;
import server.ThreadServer;

public class MainScreen implements Screen {
    Label labelPort;
    Stage stage;
    ThreadServer tcpSys;
    private MyGdxGame game;
    ConcurrentLinkedQueue<String> send;
    ConcurrentLinkedQueue<String> recieve;
    TCPCommunication coms;
    int port=100;
    boolean host=false;

    //Constructor
    public MainScreen(MyGdxGame game){
        this.game=game;
    }

    @Override
    public void show() {
        try {
            System.out.println(InetAddress.getLocalHost().toString().split("/"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        send=new ConcurrentLinkedQueue<String>();
        recieve=new ConcurrentLinkedQueue<String>();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        labelPort = new Label("Port: "+port, game.skin);

        coms = new TCPCommunication();

        TextButton hostButton = new TextButton("Host",game.skin);
        TextButton clientButton = new TextButton("Client",game.skin);
        TextButton portButton = new TextButton("Change Port",game.skin);
        portButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PortInputListener listener = new PortInputListener();
                Gdx.input.getTextInput(listener, "Port Number", "", "");
            }
        });
        hostButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                tcpSys=coms.makeSocket(send, recieve, port);
                goConnectScreen();
            }
        });
        clientButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                IpInputListener listener = new IpInputListener();
                Gdx.input.getTextInput(listener, "Ip Address", "", "");
                System.out.println("Client");
            }
        });
        table.add(hostButton);
        table.row();
        table.add(clientButton);
        table.row();
        table.add(portButton);
        table.row();
        table.add(labelPort);
    }

    @Override
    public void render(float delta) {
        if (!recieve.isEmpty()){
            labelPort.setText(recieve.remove());
        }
        Gdx.gl.glClearColor(1, 2/3, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    private void goConnectScreen(){
        game.setScreen(new ConnectScreen(game, tcpSys));
        this.dispose();
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
    public class IpInputListener implements Input.TextInputListener {
        @Override
        public void input (String text) {
            System.out.println(text);
            coms.ConnectToSocket(send,recieve,text,port);
        }

        @Override
        public void canceled () {
        }
    }
    public class PortInputListener implements Input.TextInputListener {
        @Override
        public void input (String text) {
            System.out.println(text);
            try {
                port = Integer.parseInt(text);
                labelPort.setText("Port: "+port);
            } catch (java.lang.NumberFormatException e){
                labelPort.setText("Incorrect Number");
            }
        }

        @Override
        public void canceled () {
        }
    }

    @Override
    public void dispose() {

    }

}
