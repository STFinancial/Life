package application;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import application.Panel.MouseHandler;

public class LifeGUI {
	public final int PANEL_WIDTH = 1000;
	public final int PANEL_HEIGHT = 1000;
	public Universe uni;
	public Application app;
	public Panel panel;
	
	public LifeGUI(Universe uni, Application app) {
		this.app = app;
		this.uni = uni;
//		SwingUtilities.invokeLater(new Runnable (){
//			public void run() {
				createAndShowGUI();
//			}
//		});
	}
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Game of Life");
		panel = new Panel(uni, PANEL_HEIGHT, PANEL_WIDTH);
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
