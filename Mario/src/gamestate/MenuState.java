package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;
import tilemap.Background;

public class MenuState extends GameState {

	private Background bg;
	private int currentChoise;
	private String[] options = { "Bắt đầu", "Giúp đỡ", "Thoát" };

	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	private double y=GamePanel.HEIGHT;

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);

			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {

	}

	@Override
	public void update() {
		bg.update();
		if(y>140){
			y--;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// draw bg
		bg.draw(g);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("MARIO", 80, 70);

		// draw menu
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoise) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, (int)y + i * 15);
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

	@Override
	public void keyReleased(int k) {

	}
}
