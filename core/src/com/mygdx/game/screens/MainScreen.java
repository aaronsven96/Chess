package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGdxGame;


public class MainScreen implements Screen {

    Stage stage;
    private MyGdxGame game;
    public MainScreen(MyGdxGame game){
        this.game=game;
    }

    @Override
    public void show() {
        Label titleLabel=new Label("CHESS", game.skin);;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton singButton = new TextButton("Single Player",game.skin);
        TextButton multButton = new TextButton("Multiplayer",game.skin);
        singButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen(new ChessScreen(game));
            }
        });
        multButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                changeScreen(new MultiplayerScreen(game));
            }
        });
        table.add(titleLabel);
        table.row();
        table.add(singButton);
        table.row();
        table.add(multButton);
    }
    private void changeScreen(Screen screen){
        game.setScreen(screen);
        this.dispose();
    }
    @Override
    public void render(float delta) {
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
        stage.dispose();

    }
}
