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

    private static final int COL_MID_X = 1050; //Buttons related const
    private static final int COL_LEFT_X = 950;
    private static final int COL_RIGHT_X = 1150;
    private Stage stage;
    private Image background;
    private Image field;
    private Vortex vortex;
    private XButton testButton; //TODO remove any TestButton
    private XButton playSingleGameButton;
    private XButton playKnockoutTournament;
    private XButton exitButton;
    private XButton easyButton;
    private XButton mediumButton;
    private XButton hardButton;
    private XButton humanButton;
    private XButton AIButton;
    private XButton playButton;
    private XButton bestOf3;
    private XButton bestOf5;
    private XButton bestOf7;
    private int state; //goes to Utils.GAME_SINGLE or Utils.GAME_KNOCKOUT TODO this
    private XButton backButton;
    @Override
    public void show() {
        init();
        setButtonsActions();

        stage.addActor(background);
        stage.addActor(testButton);
        stage.addActor(field);
        stage.addActor(playSingleGameButton);
        stage.addActor(playKnockoutTournament);
        stage.addActor(exitButton);
        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);
        stage.addActor(humanButton);
        stage.addActor(AIButton);
        stage.addActor(playButton);
        stage.addActor(backButton);
        stage.addActor(bestOf3);
        stage.addActor(bestOf5);
        stage.addActor(bestOf7);

        vortex = new Vortex();
        stage.addActor(vortex);
        vortex.startVortex(Utils.DIFFICULTY_MEDIUM,Utils.PLAYER_AI,Utils.PLAYER_AI);


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
                ,Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        vortex.start();
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

    private void init() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Image (new Texture("core/assets/Background.jpg"));
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // Stretch if needed
        field = new Image(new Texture("core/assets/CircleField.png"));

        //Load buttons:
        testButton = new XButton(new Texture("core/assets/testButton.png"),500,500);
        //its invisible, but its there..

        // Group 1
        playSingleGameButton = new XButton(new Texture("core/assets/SingleGame.png"),COL_MID_X,450);
        playSingleGameButton.setClickedImage(new Texture("core/assets/SingleGameClicked.png"));
        playSingleGameButton.setVisible(true);

        playKnockoutTournament = new XButton(new Texture("core/assets/Tournament.png"),COL_MID_X,390);
        playKnockoutTournament.setClickedImage(new Texture("core/assets/TournamentClicked.png"));
        playKnockoutTournament.setVisible(true);

        exitButton = new XButton(new Texture("core/assets/Exit.png"),COL_MID_X,330);
        exitButton.setClickedImage(new Texture("core/assets/ExitClicked.png"));
        exitButton.setVisible(true);

        //Group 2
        easyButton = new XButton(new Texture("core/assets/Easy.png"),COL_RIGHT_X,500);
        easyButton.setClickedImage(new Texture("core/assets/EasyClicked.png"));

        mediumButton = new XButton(new Texture("core/assets/Medium.png"),COL_RIGHT_X,440);
        mediumButton.setClickedImage(new Texture("core/assets/MediumClicked.png"));
        mediumButton.setClicked(true);

        hardButton = new XButton(new Texture("core/assets/Hard.png"),COL_RIGHT_X,380);
        hardButton.setClickedImage(new Texture("core/assets/HardClicked.png"));

        humanButton = new XButton(new Texture("core/assets/vsHuman.png"),COL_LEFT_X,500);
        humanButton.setClickedImage(new Texture("core/assets/vsHumanClicked.png"));

        AIButton = new XButton(new Texture("core/assets/vsAI.png"),COL_LEFT_X,440);
        AIButton.setClickedImage(new Texture("core/assets/vsAIClicked.png"));
        AIButton.setClicked(true);

        playButton = new XButton(new Texture("core/assets/Play.png"),COL_LEFT_X,175);
        playButton.setClickedImage(new Texture("core/assets/PlayClicked.png"));

        backButton = new XButton(new Texture("core/assets/Back.png"),COL_RIGHT_X,175);
        backButton.setClickedImage(new Texture("core/assets/BackClicked.png"));


        //other group lol
        bestOf3 = new XButton(new Texture("core/assets/Bo3.png"),COL_MID_X, 330);
        bestOf3.setClickedImage(new Texture("core/assets/Bo3Clicked.png"));
        bestOf3.setClicked(true);

        bestOf5 = new XButton(new Texture("core/assets/Bo5.png"),COL_MID_X, 280);
        bestOf5.setClickedImage(new Texture("core/assets/Bo5Clicked.png"));

        bestOf7 = new XButton(new Texture("core/assets/Bo7.png"),COL_MID_X, 230);
        bestOf7.setClickedImage(new Texture("core/assets/Bo7Clicked.png"));
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

                    playSingleGameButton.setVisible(false);
                    playKnockoutTournament.setVisible(false);
                    exitButton.setVisible(false);

                    easyButton.setVisible(true);
                    mediumButton.setVisible(true);
                    hardButton.setVisible(true);
                    humanButton.setVisible(true);
                    AIButton.setVisible(true);
                    playButton.setVisible(true);
                    backButton.setVisible(true);
                    Vortex.setDifficulty(Utils.DIFFICULTY_MEDIUM);
                    Vortex.setPlayer1(Utils.PLAYER_HUMAN);
                    Vortex.setPlayer2(Utils.PLAYER_AI);
                    Vortex.setBestOf(1);
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        playKnockoutTournament.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playKnockoutTournament.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playKnockoutTournament.setClicked(false);
                if( x >= 0 && y >= 0 && x <= playKnockoutTournament.getWidth() && y <= playKnockoutTournament.getHeight()){
                    playSingleGameButton.setVisible(false);
                    playKnockoutTournament.setVisible(false);
                    exitButton.setVisible(false);

                    easyButton.setVisible(true);
                    mediumButton.setVisible(true);
                    hardButton.setVisible(true);
                    humanButton.setVisible(true);
                    AIButton.setVisible(true);
                    playButton.setVisible(true);
                    backButton.setVisible(true);

                    bestOf3.setVisible(true);
                    bestOf5.setVisible(true);
                    bestOf7.setVisible(true);

                    Vortex.setDifficulty(Utils.DIFFICULTY_MEDIUM);
                    Vortex.setPlayer1(Utils.PLAYER_HUMAN);
                    Vortex.setPlayer2(Utils.PLAYER_AI);
                    Vortex.setBestOf(3);

                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        exitButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                exitButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                exitButton.setClicked(false);
                if( x >= 0 && y >= 0 && x <= exitButton.getWidth() && y <= exitButton.getHeight()){
                    //TODO do button action
                    Gdx.app.exit();
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        easyButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                easyButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if( x >= 0 && y >= 0 && x <= easyButton.getWidth() && y <= easyButton.getHeight()){
                    //TODO do button action
                    mediumButton.setClicked(false);
                    hardButton.setClicked(false);
                    Vortex.setDifficulty(Utils.DIFFICULTY_EASY);
                    Vortex.setPlayer1(Utils.PLAYER_HUMAN);
                    Vortex.setPlayer2(Utils.PLAYER_AI);
                }
                else {
                    if(easyButton.getPreviousClicked())
                        easyButton.setClicked(true);
                    else easyButton.setClicked(false);
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        mediumButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mediumButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if( x >= 0 && y >= 0 && x <= mediumButton.getWidth() && y <= mediumButton.getHeight()){
                    //TODO do button action
                    easyButton.setClicked(false);
                    hardButton.setClicked(false);
                    Vortex.setDifficulty(Utils.DIFFICULTY_MEDIUM);
                    Vortex.setPlayer1(Utils.PLAYER_HUMAN);
                    Vortex.setPlayer2(Utils.PLAYER_AI);

                }
                else {
                    if(mediumButton.getPreviousClicked())
                        mediumButton.setClicked(true);
                    else mediumButton.setClicked(false);
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        hardButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hardButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if( x >= 0 && y >= 0 && x <= hardButton.getWidth() && y <= hardButton.getHeight()){
                    //TODO do button action
                    easyButton.setClicked(false);
                    mediumButton.setClicked(false);
                    Vortex.setDifficulty(Utils.DIFFICULTY_HARD);
                    Vortex.setPlayer1(Utils.PLAYER_HUMAN);
                    Vortex.setPlayer2(Utils.PLAYER_AI);

                }
                else {
                    if(hardButton.getPreviousClicked())
                        hardButton.setClicked(true);
                    else hardButton.setClicked(false);
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        humanButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                humanButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if( x >= 0 && y >= 0 && x <= humanButton.getWidth() && y <= humanButton.getHeight()){
                    AIButton.setClicked(false);
                    easyButton.setGreyOut(true);
                    mediumButton.setGreyOut(true);
                    hardButton.setGreyOut(true);
                    easyButton.setClicked(false);
                    mediumButton.setClicked(false);
                    hardButton.setClicked(false);
                    Vortex.setPlayer1(Utils.PLAYER_HUMAN);
                    Vortex.setPlayer2(Utils.PLAYER_HUMAN);

                }
                else {
                    if(humanButton.getPreviousClicked())
                        humanButton.setClicked(true);
                    else humanButton.setClicked(false);
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        AIButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AIButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if( x >= 0 && y >= 0 && x <= AIButton.getWidth() && y <= AIButton.getHeight()){
                    //TODO do button action
                    humanButton.setClicked(false);
                    easyButton.setGreyOut(false);
                    mediumButton.setGreyOut(false);
                    hardButton.setGreyOut(false);
                    easyButton.setClicked(false);
                    mediumButton.setClicked(true);
                    hardButton.setClicked(false);
                    Vortex.setPlayer1(Utils.PLAYER_HUMAN);
                    Vortex.setPlayer2(Utils.PLAYER_AI);
                    Vortex.setDifficulty(Utils.DIFFICULTY_MEDIUM);


                }
                else {
                    if(AIButton.getPreviousClicked())
                        AIButton.setClicked(true);
                    else AIButton.setClicked(false);
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        playButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playButton.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playButton.setClicked(false);
                if( x >= 0 && y >= 0 && x <= playButton.getWidth() && y <= playButton.getHeight()){

                    stage.addAction(Actions.sequence(
                            Actions.alpha(1)
                            ,Actions.fadeOut(0.75f)
                            ,Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());
                                }
                            })
                    ));
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

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
                    playSingleGameButton.setVisible(true);
                    playKnockoutTournament.setVisible(true);
                    exitButton.setVisible(true);
                    easyButton.setVisible(false);
                    mediumButton.setVisible(false);
                    hardButton.setVisible(false);
                    humanButton.setVisible(false);
                    AIButton.setVisible(false);
                    playButton.setVisible(false);
                    backButton.setVisible(false);
                    bestOf3.setVisible(false);
                    bestOf5.setVisible(false);
                    bestOf7.setVisible(false);
                }
                //((Game) Gdx.app.getApplicationListener()).setScreen(new PlaySingleGameScreen());

            }

        });

        bestOf3.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bestOf3.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if( x >= 0 && y >= 0 && x <= bestOf3.getWidth() && y <= bestOf3.getHeight()){
                    bestOf5.setClicked(false);
                    bestOf7.setClicked(false);
                    Vortex.setBestOf(3);
                }
                else {
                    if(bestOf3.getPreviousClicked())
                        bestOf3.setClicked(true);
                    else bestOf3.setClicked(false);
                }
            }

        });

        bestOf5.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bestOf5.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if( x >= 0 && y >= 0 && x <= bestOf5.getWidth() && y <= bestOf5.getHeight()){
                    bestOf3.setClicked(false);
                    bestOf7.setClicked(false);
                    Vortex.setBestOf(5);
                }
                else {
                    if(bestOf5.getPreviousClicked())
                        bestOf5.setClicked(true);
                    else bestOf5.setClicked(false);
                }
            }

        });

        bestOf7.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bestOf7.setClicked(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if( x >= 0 && y >= 0 && x <= bestOf7.getWidth() && y <= bestOf7.getHeight()){
                    bestOf3.setClicked(false);
                    bestOf5.setClicked(false);
                    Vortex.setBestOf(7);
                }
                else {
                    if(bestOf7.getPreviousClicked())
                        bestOf7.setClicked(true);
                    else bestOf7.setClicked(false);
                }
            }

        });
    }



}
