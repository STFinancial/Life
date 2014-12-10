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
	
	LifeButton speedUpButton;
	LifeButton speedDownButton;
	LifeButton pauseButton;
	
	
	public ButtonPanel(Universe uni, int height, int width) {
		this.uni = uni;
		ButtonHandler bhandler = new ButtonHandler();
		setPreferredSize(new Dimension(width, height));
		
		pauseButton = new LifeButton("Pause");
		pauseButton.setSize(100, 50);
		pauseButton.addActionListener(bhandler);
		speedUpButton = new LifeButton("+");
		speedUpButton.setSize(30, 30);
		speedUpButton.addActionListener(bhandler);
		speedDownButton = new LifeButton("-");
		speedDownButton.setSize(30, 30);
		speedDownButton.addActionListener(bhandler);
		
		
		add(pauseButton);
		add(speedUpButton);
		add(speedDownButton);
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
			if (s.equals(pauseButton)) {
				System.out.println("Pause button pressed");
				uni.pause();
			} else if (s.equals(speedUpButton)) {
				System.out.println("Speed Up button pressed");
				uni.increaseSpeed();
			} else if (s.equals(speedDownButton)) {
				System.out.println("Speed Down button pressed");
				uni.decreaseSpeed();
			}
			
		}
	}
}
