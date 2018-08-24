package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Board;
import com.mygdx.game.Cell;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;


public class ChessScreen implements Screen, InputProcessor {
    Label label;
    Stage stage;
    private Texture board;
    private Sprite bKing;
    private Array<Sprite> bPieces;
    private Array<Sprite> wPieces;
    private MyGdxGame game;
    private static final int spaceSize=50;
    private Board chessGame;
    public ChessScreen(MyGdxGame game, Thread tcpSys){
        this.game=game;
    }
    public ChessScreen(MyGdxGame game){
        this.game=game;
    }
    @Override
    public void show() {
        chessGame=new Board();
        Gdx.input.setInputProcessor(this.stage);
        TextureAtlas bAtlas=new TextureAtlas(Gdx.files.internal("chess/black_pieces/BlackPieces.atlas"));
        TextureAtlas wAtlas=new TextureAtlas(Gdx.files.internal("chess/white_pieces/WhitePieces.atlas"));
        bPieces=bAtlas.createSprites();
        wPieces=wAtlas.createSprites();
        board=new Texture(Gdx.files.internal("chess/board.png"));
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,400,400);
    }

    @Override
    public void render(float delta) {
        game.gameBatch.begin();
        game.gameBatch.draw(board,0,0);

        game.gameBatch.end();
    }
    private void drawPieces(Board game,SpriteBatch batch){
        for (int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                game.getCell(x,y).getPiece();
            }
        }
    }
    private Sprite getCharToSprite(char piece,Boolean isWhite){
        Array<Sprite> pieces=bPieces;
        if (isWhite){
            pieces=wPieces;
        }
        switch (piece) {
            case 'K':
                return pieces.get(0);
            case 'N':
                return pieces.get(2);
            case 'R':
                return pieces.get(4);
            case 'Q':
                return pieces.get(1);
            case 'B':
                return pieces.get(3);
            case 'P':
                return pieces.get(5);
        }
        return null;
    }

    public void drawAtPosition(Sprite piece, SpriteBatch batch,int x,int y){
        batch.draw(piece,x*spaceSize+(spaceSize/2)-(piece.getWidth()/2),y*spaceSize);
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
        board.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
