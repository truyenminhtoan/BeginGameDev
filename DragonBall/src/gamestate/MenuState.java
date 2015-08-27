/*
 * @(#)MenuState.java	1.0  Aug 19, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import tilemap.Background;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 19, 2015
 */
public class MenuState extends GameState{
	
	private Background bg;
	private int currentChoise;
	private String[] options={
			"Start",
			"Help",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm){
		this.gsm=gsm;
		try {
			bg=new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);
			
			titleColor=new Color(128,0,0);
			titleFont=new Font("Century Gothic", Font.PLAIN, 28);
			font=new Font("Arial", Font.PLAIN, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see gamestate.GameState#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see gamestate.GameState#update()
	 */
	@Override
	public void update() {
		bg.update();
	}

	/* (non-Javadoc)
	 * @see gamestate.GameState#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {
		//draw bg
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Dragon game", 80, 70);
		
		//draw menu
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i==currentChoise){
				g.setColor(Color.BLACK);
			}else{
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, 140+i*15);
		}
	}
	
	private void select(){
		if(currentChoise==0){
			//start
			gsm.setState(GameStateManager.LEVEL1STATE);
		}else if(currentChoise==1){
			//help
		}else {
			System.exit(0);
		}
	}

	/* (non-Javadoc)
	 * @see gamestate.GameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_ENTER){
			select();
		}
		if(k==KeyEvent.VK_UP){
			currentChoise--;
			if(currentChoise<0){
				currentChoise=options.length-1;
			}
		}
		if(k==KeyEvent.VK_DOWN){
			currentChoise++;
			if(currentChoise==options.length){
				currentChoise=0;
			}
		}
	}

	/* (non-Javadoc)
	 * @see gamestate.GameState#keyRelease(int)
	 */
	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
