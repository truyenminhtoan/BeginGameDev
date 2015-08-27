package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gamestate.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	// dimentions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;

	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60; // Frame per second: So khung hinh hien thi 1s
	// Thoi gian muc tieu
	private long targetTime = 1000 / FPS;

	// image
	private BufferedImage image;
	private Graphics2D g;

	private GameStateManager gsm;

	public GamePanel() {
		super();
		// setSize: Can be using when no layout manager is being used
		// setPreferredSize: else
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		// SetFocusable default is TRUE
		setFocusable(true);
		// As for requestFocus(), this method is used to make the component get
		// input focus.
		// This means that if you press any kind of key or give any input,
		// the input is heard by the respective Listener for that component.
		requestFocus();
	}

	// listener keyboard or mouse envent to parent component
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init(){
		image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D) image.getGraphics();
		running=true;
		gsm=new GameStateManager();
	}
	
	public void run(){
		init();
		long start;
		long elapsed;
		long wait;
		while(running){
			start=System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed=System.nanoTime()-start;
			wait=targetTime-elapsed/10000000;
			if(wait<0){
				wait=5;
			}
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update(){
		gsm.update();
	}
	private void draw(){
		gsm.draw(g);
	};
	
	private void drawToScreen(){
		Graphics g2=getGraphics();
		g2.drawImage(image,0,0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
