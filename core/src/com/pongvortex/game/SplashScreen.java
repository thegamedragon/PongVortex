package com.pongvortex.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class SplashScreen implements Screen{

    private Stage stage;

    @Override
    public void show() {

        stage = new Stage();
        Image splash = new Image(new Texture("core/assets/Splash.jpg"));
        Image splashTitle = new Image(new Texture("core/assets/SplashTitle.png"));

        splash.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); //Stretch (if needed) to cover all the screen
        splashTitle.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(stage); //To prevent another stage from having the input

        stage.addActor(splash); //Image is an Actor.
        stage.addActor(splashTitle);

        //Do effects -> fade in and fade out
        stage.addAction(Actions.sequence( //Up to 5 actions; Fade in and then out & switch to Main Menu
                 Actions.alpha(0)
                ,Actions.fadeIn(1.0f)
                ,Actions.delay(0.0f) //TODO CHANGE BACK TO 2.0f
                ,Actions.fadeOut(0.9f)
                ,Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                    }
                })
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
        stage.dispose();
    }
}
