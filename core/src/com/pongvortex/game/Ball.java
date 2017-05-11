package com.pongvortex.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ball {
    private Sprite redSprite;
    private Sprite greenSprite;
    public float x,y;
    public int ballColor = Utils.RED;

    public Ball(){
        redSprite=new Sprite(new Texture("core/assets/BallRed.png"));
        greenSprite=new Sprite(new Texture("core/assets/BallGreen.png"));
        x = 300;
        y = 280;
    }


    public void act(float delta){ //Called every frame

    }

    public void draw(Batch batch, float parentAlpha){
        if(ballColor==Utils.GREEN){
            greenSprite.setPosition(x,y);
            greenSprite.draw(batch, parentAlpha);
        }
        else if (ballColor == Utils.RED) {
            redSprite.setPosition(x,y);
            redSprite.draw(batch,parentAlpha);
        }
    }
}
