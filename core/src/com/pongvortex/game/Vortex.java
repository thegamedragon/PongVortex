package com.pongvortex.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Clasa se ocupa de tot jocul in sine. I se seteaza parametrii via TODO
 * si dificultatea via {@link Vortex#setDifficulty(int)}
 * si este apelat inceputul unui joc via {@link Vortex#startVortex()}
 */
public class Vortex extends Actor{

    static int difficulty;

    public void startVortex(){
        reset(); //Resets the game to an initial state

        //Other operations
    }

    private void reset(){
        difficulty = Utils.DIFFICULTY_MEDIUM;
        //TODO reset paddles and ball to the default state!!
    }

    static public void setDifficulty(int difficulty_){ difficulty = difficulty_;}

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
