package com.pongvortex.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlaySingleGameScreen implements Screen{

    private Stage stage;
    private Image background;
    private Image backgroundField;
    private Vortex vortex;
    private Image teamScore;
    private XButton backButton;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Image (new Texture("core/assets/Background.jpg"));
        backgroundField = new Image (new Texture("core/assets/CircleField.png"));
        teamScore = new Image(new Texture("core/assets/Score.png"));
        vortex = new Vortex();

        backButton = new XButton(new Texture("core/assets/Back.png"),995.0f,200.0f);
        backButton.setClickedImage(new Texture("core/assets/BackClicked.png"));
        backButton.setVisible(true);

        setButtonActions();

        stage.addActor(background);
        stage.addActor(backgroundField);
        stage.addActor(vortex);
        stage.addActor(teamScore);
        stage.addActor(backButton);

        vortex.startVortex();

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
        stage.dispose();
    }


    private void setButtonActions(){
        backButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backButton.setClicked(false);
                if( x >= 0 && y >= 0 && x <= backButton.getWidth() && y <= backButton.getHeight()){
                    //TODO do button action
                    stage.addAction(Actions.sequence(
                             Actions.alpha(1.0f)
                            ,Actions.fadeOut(0.5f)
                            ,Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                                }
                            })
                    ));
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());
            }
        });
    }
}
