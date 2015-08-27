/*
 * @(#)Level1State.java	1.0  Aug 19, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import audio.AudioPlayer;
import main.GamePanel;
import tilemap.Background;
import tilemap.TileMap;
import entity.Enemy;
import entity.Explosion;
import entity.HUB;
import entity.Player;
import entity.enemy.Slugger;

/**
 * Mô tả đoạn code bên dưới
 * 
 * @author: toantm
 * @since: Aug 19, 2015
 */
public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;
	private Player player;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	
	private AudioPlayer bgMusic;
	
	private HUB hub;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.07);

		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
		player = new Player(tileMap);
		player.setPosition(100, 100);
		
		//add Enemy
		populateEnemies();
		
		explosions=new ArrayList<Explosion>();
		
		hub=new HUB(player);
		
		bgMusic=new AudioPlayer("/Music/level1-1.mp3");
		bgMusic.play();
	}
	
	private void populateEnemies(){
		enemies=new ArrayList<Enemy>();
		Slugger s;
		Point[] points=new Point[]{
				new Point(200, 200),
				new Point(860, 200),
				new Point(1525, 200),
				new Point(1680, 200),
				new Point(1800, 200)
		};
		for (int i = 0; i < points.length; i++) {
			s=new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
	}

	public void update() {
		// update player
		player.update();
		tileMap.setPosition(GamePanel.WIDTH/2-player.getx(), GamePanel.HEIGHT/2-player.gety());
		
		//set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		player.checkAttack(enemies);
		
		//update all enemy
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e=enemies.get(i);
			e.update();
			if(e.isDead()){
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
			}
		}
		
		//update all explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()){
				explosions.remove(i);
				i--;
			}
		}
		
	};

	public void draw(Graphics2D g) {

		// clear screen
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);
		
		//draw enemy
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		//draw explosion
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}
		
		//draw hud
		hub.draw(g);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if (k == KeyEvent.VK_UP) {
			player.setUp(true);
		}
		if (k == KeyEvent.VK_DOWN) {
			player.setDown(true);
		}
		if (k == KeyEvent.VK_W) {
			player.setJumping(true);
		}
		if (k == KeyEvent.VK_E) {
			player.setGliding(true);
		}
		if (k == KeyEvent.VK_R) {
			player.setScratching();
		}
		if (k == KeyEvent.VK_F) {
			player.setFiring();
		}
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		if (k == KeyEvent.VK_UP) {
			player.setUp(false);
		}
		if (k == KeyEvent.VK_DOWN) {
			player.setDown(false);
		}
		if (k == KeyEvent.VK_W) {
			player.setJumping(false);
		}
		if (k == KeyEvent.VK_E) {
			player.setGliding(false);
		}
	}

}
