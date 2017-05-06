package com.pongvortex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenuScreen implements Screen {

    private Stage stage;
    private Image background;
    private XButton testButton; //TODO remove any TestButton

    @Override
    public void show() {
        //TODO move initializations to their own init function (incl and esp buttons)
        stage = new Stage();
        background = new Image (new Texture("core/assets/Background.jpg"));
        testButton=new XButton(new Texture("core/assets/testButton.png"),500,500);

        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // Stretch if needed

        stage.addActor(background);
        stage.addActor(testButton);
//        testButton.setVisible(false); //TODO MANAGE BUTTONS VIA VISIBILITY


        //FadeIn when switched to this.
        stage.addAction(Actions.sequence(
                 Actions.alpha(0)
                ,Actions.fadeIn(0.5f)
        ));
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0.0f,0.0f,0.0f,1.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
