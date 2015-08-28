/*
 * @(#)GameStateManager.java	1.0  Aug 19, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package gamestate;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Mô tả đoạn code bên dưới
 * 
 * @author: toantm
 * @since: Aug 19, 2015
 */
public class GameStateManager {
	private GameState[] gameStates;
	private int currentState;

	public static final int NUMGAMESTATES = 2;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;

	public GameStateManager() {
		gameStates = new GameState[NUMGAMESTATES];

		currentState = MENUSTATE;
		loadState(currentState);
	}

	private void loadState(int state) {
		if (state == MENUSTATE) {
			gameStates[state] = new MenuState(this);
		}
		if (state == LEVEL1STATE) {
			gameStates[state] = new Level1State(this);
		}
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(state);
		currentState = state;
		loadState(currentState);
	}

	public void update() {
		try {
			if (gameStates[currentState] != null){
				gameStates[currentState].update();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		try {
			if (gameStates[currentState] != null){
				gameStates[currentState].draw(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(int k) {
		try {
			if (gameStates[currentState] != null){
				gameStates[currentState].keyPressed(k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void keyReleased(int k) {
		try {
			if (gameStates[currentState] != null){
				gameStates[currentState].keyReleased(k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
