package com.pongvortex.game;

import com.badlogic.gdx.Game;

public class PongVortex extends Game {

	
	@Override
	public void create () {
        setScreen(new SplashScreen());
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
        super.dispose();
	}
}
