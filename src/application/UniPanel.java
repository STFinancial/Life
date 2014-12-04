package application;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;


//THIS PANEL MUST BE UNIVERSE INDEPENDENT

public class UniPanel extends JPanel {
	public Universe uni;
	public int leftX;
	public int topX;
	MouseHandler handler;
	
	public UniPanel(Universe uni, int height, int width) {
		this.uni = uni;
		setPreferredSize(new Dimension(width, height));
		handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
		setBackground(Color.BLACK);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//uni.paintSquares(g, 0, 0);
		uni.paintSquares(g, handler.xIndex, handler.yIndex);
	}
	
	
	public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener{

		public int prevX;
		public int prevY;
		public int curX;
		public int curY;
		public int incX;
		public int incY;
		public int xIndex = 0;
		public int yIndex = 0;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Code here to make that square into a live square
			// TODO Handle Zooming
			//Perhaps I only allow clicking to make squares live while paused?
			//Perhaps I have a button to toggle it?
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
					
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			prevX = e.getXOnScreen();
			prevY = e.getYOnScreen();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			int panelWidth = uni.app.gui.uniPanel.getWidth();
			int panelHeight = uni.app.gui.uniPanel.getHeight();
			
			int xThresh = uni.size - (panelWidth / uni.actualSW); // the maximum uni index which x can take given the panel width
			int yThresh = uni.size - (panelHeight / uni.actualSH);
			
			//Should I switch this to just getting X?
			curX = arg0.getXOnScreen();
			curY = arg0.getYOnScreen();
			
			incX = prevX - curX; //positive means we move right
			incY = prevY - curY;
			
			System.out.println("Old X: " + xIndex);
			System.out.println("Old Y: " + yIndex);
			
			xIndex += incX / uni.actualSW;
			yIndex += incY / uni.actualSH;
			
			
			
			if (xIndex <= 0) {
				//can't have an index less than 0
				xIndex = 0;
			} else if (xThresh > 0) {
				if (xIndex > xThresh) {
					xIndex = xThresh;
				}
			} else {
				xIndex = 0;
			}
			
			if (yIndex <= 0) {
				yIndex = 0;
			} else if (yThresh > 0) {
				if (yIndex > yThresh) {
					yIndex = yThresh;
				}
			} else {
				yIndex = 0;
			}
			
			System.out.println("IncX: " + incX + "  NewIndex: " + xIndex);
			System.out.println("IncY: " + incY + "  NewIndex: " + yIndex);
			
			
			prevX = curX;
			prevY = curY;
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int rotation = e.getWheelRotation();
			if (rotation < 0) {
				uni.zoomOut(rotation);
			} else {
				uni.zoomIn(rotation);
			}
		}
		
	}
	
}
