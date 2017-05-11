package com.pongvortex.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Paddle {
    Sprite sprite;
    private float x=0,y=0;
    private float angle = 0.0f; //in degrees

    public Paddle(int paddleType){
        if(paddleType == Utils.GREEN){
            Texture t = new Texture("core/assets/PaddleGreenCurved.png");
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //sort of anti-alias.
            sprite = new Sprite(t);
            x = 45;
            y = 280;
            angle = 180.0f;
        }
        else if(paddleType == Utils.RED){
            Texture t = new Texture("core/assets/PaddleRedCurved.png");
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //sort of anti-alias.
            sprite = new Sprite(t);
            x = 675;
            y = 280;
        }
    }

    public void act(float delta){
        x = 315 * (float)Math.cos( Math.toRadians(angle)) + (675-315); //It works
        y = 315 * (float)Math.sin( Math.toRadians(angle)) + 280; //But i dont know why.
    }

    public void draw(Batch batch, float parentAlpha){
        sprite.setRotation(angle);
        sprite.setPosition(x,y);
        sprite.draw(batch,parentAlpha);
    }

    public void moveLeft(float delta){ //TODO change 80 to something AI-difficulty related (and set player speed to highest)
        angle -= 80 * delta;
    }

    public void moveRight(float delta){
        angle += 80 * delta;
    }
}
