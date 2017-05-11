package com.pongvortex.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PlaySingleGameScreen implements Screen{

    private Stage stage;
    private Image background;
    private Image backgroundField;
    private Vortex vortex;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Image (new Texture("core/assets/Background.jpg"));
        backgroundField = new Image (new Texture("core/assets/CircleField.png"));
        vortex = new Vortex();

        stage.addActor(background);
        stage.addActor(backgroundField);
        stage.addActor(vortex);

        vortex.startVortex(Utils.DIFFICULTY_MEDIUM,Utils.PLAYER_AI,Utils.PLAYER_AI);

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

        pollKeyListener();
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

    public void pollKeyListener(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            vortex.greenPaddle.moveLeft(Gdx.graphics.getDeltaTime());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            vortex.greenPaddle.moveRight(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            vortex.redPaddle.moveLeft(Gdx.graphics.getDeltaTime());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            vortex.redPaddle.moveRight(Gdx.graphics.getDeltaTime());
        }
    }
}
