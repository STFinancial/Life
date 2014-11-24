package application;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import application.UniPanel.MouseHandler;

public class LifeGUI {
	public final int UNI_PANEL_WIDTH = 500;
	public final int UNI_PANEL_HEIGHT = 500;
	public final int B_PANEL_WIDTH = 400;
	public final int B_PANEL_HEIGHT = 500;
	public final int MIN_SQUARE_SIZE = 2;
	public Universe uni;
	public Application app;

	public ButtonPanel bPanel;
	public UniPanel uniPanel;
	public JFrame frame;

	public LifeGUI(Universe uni, Application app) {
		this.app = app;
		this.uni = uni;
		// SwingUtilities.invokeLater(new Runnable (){
		// public void run() {
		createAndShowGUI();
		// }
		// });
	}

	public void createAndShowGUI() {
		frame = new JFrame("Game of Life");
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(1000, 600));
		mainPanel.setLayout(new FlowLayout());
		frame.add(mainPanel);
		uniPanel = new UniPanel(uni, UNI_PANEL_HEIGHT, UNI_PANEL_WIDTH);
		bPanel = new ButtonPanel(uni, B_PANEL_HEIGHT, B_PANEL_WIDTH);
		frame.setSize(new Dimension(1000, 600));
		//frame.setContentPane(uniPanel);
		mainPanel.add(uniPanel);
		mainPanel.add(bPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
