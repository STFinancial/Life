package application;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Panel extends JPanel {
	public Universe uni;
	
	public Panel(Universe uni, int height, int width) {
		this.uni = uni;
		setPreferredSize(new Dimension(height, width));
		addMouseListener(new MouseHandler());
		setBackground(Color.BLACK);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		uni.paintSquares(g);
	}
	
	
	public class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
