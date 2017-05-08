package com.pongvortex.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class XButton extends Actor {

    private Image buttonImage;
    private Image buttonClickedImage;
    private boolean clicked;

    public XButton (Texture texture, float x, float y){
        buttonImage = new Image(texture);
        buttonClickedImage = buttonImage;
        setX(x);
        setY(y);
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setVisible(true);
        clicked = false;
        buttonImage.setPosition(getX(),getY(), Align.center);
        buttonClickedImage.setPosition(getX(),getY(), Align.center);
        setBounds(getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());
    }

    public void setClickedImage(Texture texture){
        buttonClickedImage = new Image(texture);
        buttonClickedImage.setSize(getWidth(),getHeight());
        buttonClickedImage.setPosition(getX(),getY(), Align.bottomLeft);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!clicked) buttonImage.draw(batch,parentAlpha);
        else buttonClickedImage.draw(batch,parentAlpha);
    }

    public void setClicked(boolean clicked){
        this.clicked = clicked;
    }
}
