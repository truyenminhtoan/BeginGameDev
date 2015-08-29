/*
 * @(#)TestDraw.java	1.0  Aug 28, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package main;

import gamestate.GameStateManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Mô tả đoạn code bên dưới
 * 
 * @author: toantm
 * @since: Aug 28, 2015
 */
public class TestDraw extends JPanel implements Runnable {

	public boolean isRunning = false;
	public int radius = 25;

	public void start() {
		isRunning = true;
		new Thread(this).start();
	}

	public void stop() {
		isRunning = false;
	}

	public void paint(Graphics g) {
		// Create Graphics2D object, cast g as a Graphics2D
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(0, 0, 150, 150);

		g2d.setColor(Color.BLACK);
		g2d.drawOval((150 / 2 - radius), (150 / 2 - radius), radius * 2,
				radius * 2);
	}

	public void run() {

		while (isRunning) {
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		TestDraw test = new TestDraw();
		frame.getContentPane().add(test);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
		test.start();
	}

}
