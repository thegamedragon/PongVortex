package com.pongvortex.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    static int player1 = Utils.PLAYER_AI;
    static int player2 = Utils.PLAYER_AI;

    Ball ball = new Ball();
    public Paddle greenPaddle = new Paddle(Utils.GREEN);
    public Paddle redPaddle = new Paddle(Utils.RED);

    public void startVortex(){
        reset(); //Resets the game to an initial state

        //Other operations
    }

    public void startVortex(int difficulty, int player1, int player2){

    }

    private void reset(){
//        difficulty = Utils.DIFFICULTY_MEDIUM;
//        player1=Utils.PLAYER_AI;
//        player2=Utils.PLAYER_AI;
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
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        act() for each object
        ball.act(delta);
        greenPaddle.act(delta);
        redPaddle.act(delta);
        checkInGame();
        CollisionDetect();
    }

    public void checkInGame(){
        if(ball.inGame==true && distanta(ball.xCentru, ball.yCentru, 377, 360) > 315){
            ball.inGame = false;
            redPaddle.inGame = false;
            greenPaddle.inGame = false;
            ball.speedModifier = 1;
            setScore();
        }
    }

    public void setScore(){
        if(ball.ballColor==Utils.GREEN)
            greenPaddle.score++;
        else
            redPaddle.score++;
        System.out.println("GREEN : " + greenPaddle.score + " - " + redPaddle.score + " : RED\n");
    }

    public void reflect(float unghi) {
        float raza = distanta(ball.xCentru, ball.yCentru, ball.xCentru-ball.speedX, ball.yCentru-ball.speedY);
        ball.speedX = raza * (float)Math.cos( Math.toRadians(unghi+180));
        ball.speedY = raza * (float)Math.sin( Math.toRadians(unghi+180));
        ball.speedModifier += ball.speedModifier*0.05 ;
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

    public float distanta(float x1, float y1, float x2, float y2) {
        return (float)(sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)));
    }
}
