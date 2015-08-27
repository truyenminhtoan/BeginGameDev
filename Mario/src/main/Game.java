package main;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame frame=new JFrame("Mario");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
	}

}
