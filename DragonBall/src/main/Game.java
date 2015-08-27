/*
 * @(#)Game.java	1.0  Aug 19, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package main;

import javax.swing.JFrame;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 19, 2015
 */
public class Game {
	public static void main(String[] args){
		JFrame frame=new JFrame("Dragon");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
	}
}
