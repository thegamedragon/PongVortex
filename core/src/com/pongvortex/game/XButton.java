package com.pongvortex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class XButton extends Actor {

    private Image buttonImage;
    private Image buttonClickedImage;
    private boolean clicked;
    private boolean previousClicked;
    private boolean greyedOut;

    public XButton (Texture texture, float x, float y){
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        buttonImage = new Image(texture);
        buttonClickedImage = buttonImage;
        setX(x);
        setY(y);
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setVisible(false);
        clicked = false;
        previousClicked = false;
        greyedOut = false;
        buttonImage.setPosition(getX(),getY(), Align.center);
        buttonClickedImage.setPosition(getX(),getY(), Align.center);
        setBounds(getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());
    }

    public void setClickedImage(Texture texture){
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        buttonClickedImage = new Image(texture);
        buttonClickedImage.setSize(getWidth(),getHeight());
        buttonClickedImage.setPosition(getX(),getY(), Align.bottomLeft);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!clicked) {
            if(greyedOut){
//                Gdx.gl20.glBlendFunc(GL20.GL_ONE_MINUS_DST_COLOR, GL20.GL_ZERO);
                buttonImage.draw(batch, parentAlpha);
//                Gdx.gl20.glBlendFunc(GL20.GL_ZERO, GL20.GL_ONE);
            }
            else buttonImage.draw(batch, parentAlpha);
        }
        else buttonClickedImage.draw(batch,parentAlpha);
    }

    public void setClicked(boolean clicked){
        previousClicked = this.clicked;
        this.clicked = clicked;
    }
    public boolean getPreviousClicked () {return this.previousClicked;}

    public void setGreyOut(boolean greyedOut){
        if(!greyedOut){ //Set to false
            this.greyedOut = false;
            this.setTouchable(Touchable.enabled);
            buttonImage.setColor(1.0f,1.0f,1.0f,1.0f);
        }
        else { //Set to true
            this.greyedOut = true;
            this.setTouchable(Touchable.disabled);
            buttonImage.setColor(0.6f,0.6f,0.6f,1);
        }
    }
}
