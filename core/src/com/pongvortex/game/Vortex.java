package com.pongvortex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

/**
 * Clasa se ocupa de tot jocul in sine. I se seteaza jucatorii
 * via {@link Vortex#setPlayer1(int)}  si {@link Vortex#setPlayer2(int)}
 * si dificultatea via {@link Vortex#setDifficulty(int)}
 * si este apelat inceputul unui joc via {@link Vortex#startVortex()}
 */
public class Vortex extends Actor{

    static int difficulty = Utils.DIFFICULTY_MEDIUM;
    static int player1 = Utils.PLAYER_HUMAN;
    static int player2 = Utils.PLAYER_AI;

    int currentDifficulty = Utils.DIFFICULTY_MEDIUM;
    int currentPlayer1 = Utils.PLAYER_HUMAN;
    int currentPlayer2 = Utils.PLAYER_AI;

    static int screen = Utils.SCREEN_MENU;

    Ball ball = new Ball(this);
    public Paddle greenPaddle = new Paddle(Utils.GREEN);
    public Paddle redPaddle = new Paddle(Utils.RED);
    private boolean changeRandom = true;
    private float targetAngleRed = 180.0f;
    private float targetAngleGreen = 0.0f;

    public Image greenScore = new Image(new Texture("core/assets/GREEN/0.png"));
    public Image redScore = new Image (new Texture ("core/assets/RED/0.png"));

    public void startVortex(){
        reset(); //Resets the game to an initial state
        //if(player1 == Utils.PLAYER_AI) greenPaddle.speed = 15;
        if(player1 == Utils.PLAYER_HUMAN) greenPaddle.speed = 40;

        if(player2 == Utils.PLAYER_AI){
            if(difficulty == Utils.DIFFICULTY_EASY) redPaddle.speed = 10;
            if(difficulty == Utils.DIFFICULTY_MEDIUM) redPaddle.speed = 15;
            if(difficulty == Utils.DIFFICULTY_HARD) redPaddle.speed = 30;
        }
        if(player2 == Utils.PLAYER_HUMAN) redPaddle.speed = 40;
        if(player1 == Utils.PLAYER_AI) {
            greenPaddle.speed = 50;
            redPaddle.speed = 50;
        }
        currentDifficulty = difficulty;
        currentPlayer1 = player1;
        currentPlayer2 = player2;
        //Other operations

        greenScore.setPosition(908,365);
        redScore.setPosition(1008,365);
    }

    public void startVortex(int difficulty, int player1, int player2){
        setPlayer2(player2);
        setPlayer1(player1);
        setDifficulty(difficulty);
        startVortex();
    }

    private void reset(){
        //TODO reset paddles and ball to the default state!! PERHAPS COUNTDOWN?
    }

    static public void setDifficulty(int difficulty_){ difficulty = difficulty_;}
    static public void setPlayer1 (int playerType) {player1 = playerType;}
    static public void setPlayer2 (int playerType) {player2 = playerType;}

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        draw() for each object
        ball.draw(batch, parentAlpha);
        greenPaddle.draw(batch, parentAlpha);
        redPaddle.draw(batch, parentAlpha);
        if(currentPlayer1==Utils.PLAYER_HUMAN){
            greenScore.draw(batch,parentAlpha);
            redScore.draw(batch,parentAlpha);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        act() for each object

        pollKeyListener();
        ball.act(delta);
        greenPaddle.act(delta);
        redPaddle.act(delta);
        checkInGame();
        //CollisionDetect();
    }

    public void checkInGame(){
        if(ball.inGame==true && distanta(ball.xCentru, ball.yCentru, 377, 360) > 315){
            ball.inGame = false;
            redPaddle.inGame = false;
            greenPaddle.inGame = false;
            ball.speedModifier = 1;
            setScore();
            changeRandom = true;
            targetAngleGreen = 180.0f;
            targetAngleRed = 0.0f;
            if(screen == Utils.SCREEN_MENU) {
                getStage().addAction(Actions.sequence(
                         Actions.delay(1.0f)
                        ,Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                start();
                            }
                        })
                ));
            }
        }
    }

    public void setScore(){
        if(ball.ballColor==Utils.GREEN){
            greenPaddle.score++;
            greenScore.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("core/assets/GREEN/"+greenPaddle.score+".png"))));
        }
        else{
            redPaddle.score++;
            redScore.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("core/assets/RED/"+redPaddle.score+".png"))));
        }
//        System.out.println("GREEN : " + greenPaddle.score + " - " + redPaddle.score + " : RED\n");
    }

    public void reflect(float unghi) {
        float raza = distanta(ball.xCentru, ball.yCentru, ball.xCentru-ball.speedX, ball.yCentru-ball.speedY);
        ball.speedX = raza * (float)Math.cos( Math.toRadians(unghi+180+(float)Math.random() * 20.0f - 10f));
        ball.speedY = raza * (float)Math.sin( Math.toRadians(unghi+180+(float)Math.random() * 20.0f - 10f));
        if(ball.speedModifier < 5.0f) ball.speedModifier += ball.speedModifier*0.05 ;
        changeRandom = true;
    }

    public void CollisionDetect() {

        float d1 = distanta(ball.xCentru, ball.yCentru, redPaddle.dot1x, redPaddle.dot1y);
        float d2 = distanta(ball.xCentru, ball.yCentru, redPaddle.dot2x, redPaddle.dot2y);
        float d3 = distanta(redPaddle.dot1x, redPaddle.dot1y, redPaddle.dot2x, redPaddle.dot2y);

        float d4 = distanta(ball.xCentru, ball.yCentru, greenPaddle.dot1x, greenPaddle.dot1y);
        float d5 = distanta(ball.xCentru, ball.yCentru, greenPaddle.dot2x, greenPaddle.dot2y);
        float d6 = distanta(greenPaddle.dot1x, greenPaddle.dot1y, greenPaddle.dot2x, greenPaddle.dot2y);

        if( d1+d2<d3+4 && d1+d2>d3-4 && ball.ballColor==Utils.GREEN) {
            reflect(redPaddle.angle);
            ball.ballColor = Utils.RED;
        }
        if( d4+d5<d6+4 && d4+d5>d6-4 && ball.ballColor==Utils.RED) {
            reflect(greenPaddle.angle);
            ball.ballColor = Utils.GREEN;
        }
    }

    public boolean CollisionDetectB() {

        float d1 = distanta(ball.xCentru, ball.yCentru, redPaddle.dot1x, redPaddle.dot1y);
        float d2 = distanta(ball.xCentru, ball.yCentru, redPaddle.dot2x, redPaddle.dot2y);
        float d3 = distanta(redPaddle.dot1x, redPaddle.dot1y, redPaddle.dot2x, redPaddle.dot2y);

        float d4 = distanta(ball.xCentru, ball.yCentru, greenPaddle.dot1x, greenPaddle.dot1y);
        float d5 = distanta(ball.xCentru, ball.yCentru, greenPaddle.dot2x, greenPaddle.dot2y);
        float d6 = distanta(greenPaddle.dot1x, greenPaddle.dot1y, greenPaddle.dot2x, greenPaddle.dot2y);

        if( d1+d2<d3+4 && d1+d2>d3-4 && ball.ballColor==Utils.GREEN) {
            reflect(redPaddle.angle);
            ball.ballColor = Utils.RED;
            return true;
        }
        if( d4+d5<d6+4 && d4+d5>d6-4 && ball.ballColor==Utils.RED) {
            reflect(greenPaddle.angle);
            ball.ballColor = Utils.GREEN;
            return true;
        }
        return false;
    }

    public float distanta(float x1, float y1, float x2, float y2) {
        return (float)(sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)));
    }

    public void pollKeyListener(){
        //float ballAngle = (float) (Math.atan2(ball.speedY, ball.speedX) * (180 / Math.PI));
        float ballAngle = findBallAngle() ;
        while(ballAngle < 0.0f) ballAngle+= 360.0f;
        if(ball.inGame ){
        float direction = 0;
        if(currentPlayer2 == Utils.PLAYER_HUMAN){
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.UP)){
                redPaddle.moveCounterclockwise(Gdx.graphics.getDeltaTime());
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                redPaddle.moveClockwise(Gdx.graphics.getDeltaTime());
            }
        }
        else if (currentPlayer2 == Utils.PLAYER_AI){
            if(ball.ballColor == Utils.GREEN){ //move to ball + - 20deg
                if(changeRandom) {targetAngleRed = ballAngle + (float)Math.random() * 20.0f - 10.0f;changeRandom=false;}

            }
            else if(ball.ballColor == Utils.RED){ //Move to opposite of ball
                targetAngleRed = ballAngle >= 180.0f ? ballAngle - 180.0f : ballAngle + 180.0f;
            }
            if ( targetAngleRed >= 360.0f) targetAngleRed-= 360.0f;
            if ( targetAngleRed <  0.0f ) targetAngleRed += 360.0f;
            if(Math.abs(targetAngleRed - redPaddle.angle) > 2.0f ){
            //{
                //System.out.println(targetAngleRed - redPaddle.angle);

                if( targetAngleRed-redPaddle.angle >  180.0f ) direction = targetAngleRed - redPaddle.angle - 360.0f;
                else if( targetAngleRed-redPaddle.angle < -180.0f ) direction = targetAngleRed - redPaddle.angle + 360.0f;
                else direction = targetAngleRed - redPaddle.angle;
                if(direction <= 0.0f && direction > -180.0f) redPaddle.moveClockwise(Gdx.graphics.getDeltaTime());
                else if(direction > 0.0f && direction <=180.0f ) redPaddle.moveCounterclockwise(Gdx.graphics.getDeltaTime());

            }
        }

        if(currentPlayer1 == Utils.PLAYER_HUMAN){
            if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S)){
                greenPaddle.moveCounterclockwise(Gdx.graphics.getDeltaTime());
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.W)){
                greenPaddle.moveClockwise(Gdx.graphics.getDeltaTime());
            }
        }
        else if (currentPlayer1 == Utils.PLAYER_AI){
            if(ball.ballColor == Utils.RED){ //move to ball + - 20deg
                if(changeRandom) {targetAngleGreen = ballAngle + (float)Math.random() * 20.0f - 10.0f;changeRandom=false;}
            }
            else if(ball.ballColor == Utils.GREEN){ //Move to opposite of ball
                targetAngleGreen = ballAngle >= 180.0f ? ballAngle - 180.0f : ballAngle + 180.0f;
            }
            if ( targetAngleGreen >= 360.0f) targetAngleGreen-= 360.0f;
            if ( targetAngleGreen <  0.0f ) targetAngleGreen += 360.0f;
            if(Math.abs(targetAngleGreen - greenPaddle.angle) > 2.0f ){

                if( targetAngleGreen-greenPaddle.angle >  180.0f ) direction = targetAngleGreen - greenPaddle.angle - 360.0f;
                else if( targetAngleGreen-greenPaddle.angle < -180.0f ) direction = targetAngleGreen - greenPaddle.angle + 360.0f;
                else direction = targetAngleGreen - greenPaddle.angle;
                if(direction <= 0.0f && direction > -180.0f) greenPaddle.moveClockwise(Gdx.graphics.getDeltaTime());
                else if(direction > 0.0f && direction <=180.0f ) greenPaddle.moveCounterclockwise(Gdx.graphics.getDeltaTime());



            }
        }}

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            start();
        }
    }

    private float findBallAngle(){
        float circleX, circleY;
        float circleR;
        float x0, x1, y0, y1;
        float t;

        circleX = 377.0f; //h
        circleY = 360.0f; //k
        circleR = 315.0f;

        x0 = ball.xCentru;
        y0 = ball.yCentru;
        x1 = x0 + ball.speedX;
        y1 = y0 + ball.speedY;

//        x0 = 377.0f;
//        y0 = 520.0f;
//        x1 = x0 - 1000;
//        y1 = y0 ;

        float a = (x1-x0)*(x1-x0) + (y1-y0)*(y1-y0);
        float b = 2*(x1-x0)*(x0-circleX) + 2*(y1-y0)*(y0-circleY);
        float c = (x0-circleX)*(x0-circleX) + (y0-circleY)*(y0-circleY) - circleR*circleR;

        t = (2*c) / ( -b - (float)Math.sqrt((double)(b*b-4*a*c)) );

        float x_i, y_i;
        x_i = (x1-x0)*t+x0;
        y_i = (y1-y0)*t+y0;

        x_i -= circleX;
        y_i -= circleY;

        return (float)  (Math.atan2(y_i, x_i) * (180 / Math.PI));
    }
    void start(){
        // START BILA
        ball.inGame = true;
        redPaddle.inGame = true;
        greenPaddle.inGame = true;

        if(ball.ballColor == Utils.GREEN){
            ball.speedX = 300;
            ball.speedY = 0;
        }
        else{
            ball.speedX = -300;
            ball.speedY = 0;
        }
        changeRandom = true;
        targetAngleRed = 180.0f;
        targetAngleGreen = 0.0f;
    }
}
