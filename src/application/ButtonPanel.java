package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{
	Universe uni;
	
	LifeButton b1;
	LifeButton b2;
	LifeButton clearButton;
	
	
	public ButtonPanel(Universe uni, int height, int width) {
		this.uni = uni;
		
		setPreferredSize(new Dimension(width, height));
		
		clearButton = new LifeButton("Clear");
		clearButton.setSize(100, 50);
		clearButton.addActionListener(new ButtonHandler());
		add(clearButton);
		repaint();
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.RED);
		setOpaque(true);
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			LifeButton s = (LifeButton) arg0.getSource();
			//for now assume we have a button
			if (s.equals(clearButton)) {
				System.out.println("Clear button pressed");
				uni.clear();
			}
			
		}
	}
}
