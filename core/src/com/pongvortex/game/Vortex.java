package com.pongvortex.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Clasa se ocupa de tot jocul in sine. I se seteaza jucatorii
 * via {@link Vortex#setPlayer1(int)}  si {@link Vortex#setPlayer2(int)}
 * si dificultatea via {@link Vortex#setDifficulty(int)}
 * si este apelat inceputul unui joc via {@link Vortex#startVortex()}
 */
public class Vortex extends Actor{

    static int difficulty = Utils.DIFFICULTY_MEDIUM;
    static int player1 = Utils.PLAYER_AI;
    static int player2 = Utils.PLAYER_AI;

    public void startVortex(){
        reset(); //Resets the game to an initial state

        //Other operations
    }

    public void startVortex(int difficulty, int player1, int player2){
        
    }

    private void reset(){
//        difficulty = Utils.DIFFICULTY_MEDIUM;
//        player1=Utils.PLAYER_AI;
//        player2=Utils.PLAYER_AI;
        //TODO reset paddles and ball to the default state!! PERHAPS COUNTDOWN?
    }

    static public void setDifficulty(int difficulty_){ difficulty = difficulty_;}
    static public void setPlayer1 (int playerType) {player1 = playerType;}
    static public void setPlayer2 (int playerType) {player2 = playerType;}

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        draw() for each object

    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        act() for each object
    }
}
