package com.pongvortex.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class XButton extends Actor {

    private Image buttonImage;

    public XButton (Texture texture, float x, float y){
        this.buttonImage = new Image(texture);
        this.setX(x);
        this.setY(y);
        this.setVisible(true);
        buttonImage.setPosition(getX(),getY(), Align.center);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        buttonImage.draw(batch,parentAlpha);
    }
}
