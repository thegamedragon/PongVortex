package com.pongvortex.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class XButton extends Actor {

    private Image buttonImage;
    private Image buttonClickedImage;
    public boolean clicked;

    public XButton (Texture texture, float x, float y){
        this.buttonImage = new Image(texture);
        this.buttonClickedImage = buttonImage;
        this.setX(x);
        this.setY(y);
        this.setWidth(texture.getWidth());
        this.setHeight(texture.getHeight());
        this.setVisible(true);
        this.clicked = false;
        this.buttonImage.setPosition(getX(),getY(), Align.center);
        this.buttonClickedImage.setPosition(getX(),getY(), Align.center);
        setBounds(getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());
    }

    public void setClickedImage(Texture texture){
        this.buttonClickedImage = new Image(texture);
        this.buttonClickedImage.setSize(getWidth(),getHeight());
        this.buttonClickedImage.setPosition(getX(),getY(), Align.bottomLeft);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!clicked) buttonImage.draw(batch,parentAlpha);
        else buttonClickedImage.draw(batch,parentAlpha);
    }
}
