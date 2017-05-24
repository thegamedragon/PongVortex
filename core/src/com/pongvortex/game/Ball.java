package com.pongvortex.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ball {
    private Sprite redSprite;
    private Sprite greenSprite;
    public float x,y;
    public float xCentru, yCentru;
    public int ballColor = Utils.GREEN;
    public float speedX = 0;
    public float speedY = 0;
    public float speedModifier = 1;
    public boolean inGame = false;
    private Vortex vortex;

    public Ball(Vortex v){
        redSprite=new Sprite(new Texture("core/assets/BallRed.png"));
        greenSprite=new Sprite(new Texture("core/assets/BallGreen.png"));
        x = 364;
        y = 346;
        vortex = v;
    }

    public void act(float delta){ //Called every frame
        if(inGame==true){
            x += speedX * delta * speedModifier;
            y += speedY * delta * speedModifier;
            xCentru = x+14;
            yCentru = y+14;
            vortex.CollisionDetect();


//            float i;
//            for(i = 0.0f; i <= delta ; i+=0.00001f){
//                x += speedX * 0.00001f * speedModifier;
//                y += speedY * 0.00001f * speedModifier;
//                xCentru = x+14;
//                yCentru = y+14;
//                if(vortex.CollisionDetectB() == true){
//                    x += speedX * (delta - i) * speedModifier;
//                    y += speedY * (delta - i) * speedModifier;
//                    xCentru = x+14;
//                    yCentru = y+14;
//                    break;
//                }
//            }
        }
        else{

            x = 364;
            y = 346;
            xCentru = x+14;
            yCentru = y+14;
            speedX = 0;
            speedY = 0;
        }
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
