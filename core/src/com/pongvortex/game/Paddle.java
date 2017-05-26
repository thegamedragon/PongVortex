package com.pongvortex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Paddle {
    Sprite sprite;
    Sprite dot1;
    Sprite dot2;
    public float x=0,y=0;
    public float dot1x, dot1y, dot2x, dot2y;
    public float angle = 360.0f; //in degrees
    public boolean inGame = false;
    public int paddleColor;
    public int score = 0;
    public int setScore = 0;
    public int speed = 80;

    public Paddle(int paddleType){
        paddleColor = paddleType;
        if(paddleType == Utils.GREEN){
            Texture t = new Texture("core/assets/PaddleGreenCurved.png");
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //sort of anti-alias.
            sprite = new Sprite(t);
            x = 45;
            y = 280;
            angle = 180.0f;
            dot1x = 76;
            dot1y = 285;
            dot2x = 76;
            dot2y = 436;
        }
        else if(paddleType == Utils.RED){
            Texture t = new Texture("core/assets/PaddleRedCurved.png");
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //sort of anti-alias.
            sprite = new Sprite(t);
            x = 675;
            y = 280;
            dot1x = 677;
            dot1y = 285;
            dot2x = 677;
            dot2y = 436;
        }

        Texture pixel = new Texture("core/assets/dot.png");
        pixel.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //sort of anti-alias.
        dot1 = new Sprite(pixel);
        dot2 = new Sprite(pixel);
    }

    public void act(float delta){
        if(inGame==true){
            x = 315 * (float)Math.cos( Math.toRadians(angle)) + 360;
            y = 315 * (float)Math.sin( Math.toRadians(angle)) + 280;

            dot1x = 310 * (float)Math.cos( Math.toRadians(angle-14)) + 377;
            dot1y = 310 * (float)Math.sin( Math.toRadians(angle-14)) + 360;

            dot2x = 310 * (float)Math.cos( Math.toRadians(angle+14)) + 377;
            dot2y = 310 * (float)Math.sin( Math.toRadians(angle+14)) + 360;
        }
        else{
            if(paddleColor == Utils.GREEN){
                x = 45;
                y = 280;
                angle = 180.0f;
                dot1x = 76;
                dot1y = 285;
                dot2x = 76;
                dot2y = 436;
            }
            else{
                x = 675;
                y = 280;
                angle = 0.0f;
                dot1x = 677;
                dot1y = 285;
                dot2x = 677;
                dot2y = 436;
            }
        }
    }

    public void draw(Batch batch, float parentAlpha){
        sprite.setRotation(angle);
        sprite.setPosition(x,y);
        sprite.draw(batch,parentAlpha);
    }

    public void moveClockwise(float delta){ //TODO change 80 to something AI-difficulty related (and set player speed to highest)
        angle -= speed * delta;
        if(angle>360)
            angle=0+speed * delta;
        if(angle<0)
            angle=360-speed * delta;
    }

    public void moveCounterclockwise(float delta){
        angle += speed * delta;
        if(angle>360)
            angle=0+speed * delta;
        if(angle<0)
            angle=360-speed * delta;
    }
}
