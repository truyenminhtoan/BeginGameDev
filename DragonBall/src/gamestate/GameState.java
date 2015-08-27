/*
 * @(#)GameState.java	1.0  Aug 19, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package gamestate;

import java.awt.Graphics2D;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 19, 2015
 */
public abstract class GameState {
	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
