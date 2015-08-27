package gamestate;

import java.awt.Graphics2D;

public class GameStateManager {
	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES=2;
	public static final int MENUSTATE=0;
	public static final int LEVEL1STATE=1;
	
	public GameStateManager(){
		gameStates=new GameState[NUMGAMESTATES];
		
		currentState=MENUSTATE;
		loadState(currentState);
//		gameStates.add(new MenuState(this));
//		gameStates.add(new Level1State(this));
	}
	
	private void loadState(int state){
		if(state==MENUSTATE){
			gameStates[state]=new MenuState(this);
		}
		if(state==LEVEL1STATE){
			gameStates[state]=new Level1State(this);
		}
	}
	
	private void unloadState(int state){
		gameStates[state]=null;
	}
	
	public void setState(int state){
		unloadState(state);
		currentState=state;
		loadState(currentState);
	}
	
	public void update(){
		try {
			if(gameStates[currentState]!=null){
				gameStates[currentState].update();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g){
		try {
			if(gameStates[currentState]!=null)
				gameStates[currentState].draw(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed(int k){
		try {
			if(gameStates[currentState]!=null)
				gameStates[currentState].keyPressed(k);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyReleased(int k){
		try {
			if(gameStates[currentState]!=null)
				gameStates[currentState].keyReleased(k);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
