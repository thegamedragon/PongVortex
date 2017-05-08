package com.pongvortex.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {

    private Stage stage;
    private Image background;
    private XButton testButton; //TODO remove any TestButton
    private XButton playSingleGameButton;
    private XButton playKnockoutTournament;
    private XButton exitButton;
    private XButton easyButton;
    private XButton mediumButton;
    private XButton hardButton;
    private XButton humanButton;
    private XButton AIButton;
    @Override
    public void show() {
        init();
        setButtonsActions();

        stage.addActor(background);
        stage.addActor(testButton);
        stage.addActor(playSingleGameButton);
        stage.addActor(playKnockoutTournament);
        stage.addActor(exitButton);
//        stage.addActor(easyButton);
//        stage.addActor(mediumButton);
//        stage.addActor(hardButton);
//        stage.addActor(humanButton);
//        stage.addActor(AIButton);


//        TODO move initializations to their own init function (incl and esp buttons) for code simplicity


//        TODO add click listeners http://stackoverflow.com/questions/13863138/what-is-the-simplest-way-to-make-image-touchable-in-libgdx
//        TODO add fadeout effect for when game starts and such
//        testButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());
//            }
//        });


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
        stage.dispose();
    }

    private void init() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Image (new Texture("core/assets/Background.jpg"));
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // Stretch if needed

        //Load buttons:
        testButton = new XButton(new Texture("core/assets/testButton.png"),500,500);

        playSingleGameButton = new XButton(new Texture("core/assets/testButton.png"),1100,500);
        playKnockoutTournament = new XButton(new Texture("core/assets/testButton.png"),1100,400);
        exitButton = new XButton(new Texture("core/assets/testButton.png"),1100,300);
//        easyButton = new XButton(new Texture("core/assets/testButton.png"),1000,500);
//        mediumButton = new XButton(new Texture("core/assets/testButton.png"),1000,500);
//        hardButton = new XButton(new Texture("core/assets/testButton.png"),1000,500);
//        humanButton = new XButton(new Texture("core/assets/testButton.png"),1000,500);
//        AIButton = new XButton(new Texture("core/assets/testButton.png"),1000,500);
        playSingleGameButton.setClickedImage(new Texture("core/assets/testButtonClicked.png"));
    }

    private void setButtonsActions() {
        playSingleGameButton.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playSingleGameButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playSingleGameButton.setClicked(false);
                if( x >= 0 && y >= 0 && x <= playSingleGameButton.getWidth() && y <= playSingleGameButton.getHeight()){
                    //TODO do button action

                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        //TODO OTHER BUTTONSSSSSSSSSSSSSSSS
    }



}
