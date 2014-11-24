package application;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import items.LifeStructure;

public class Universe {
	public static volatile boolean interrupt;
	
	public byte[][] currentUniverse; //always the updated version
	public byte[][] nextUniverse; //used as a holder during the update loop
	public byte[][] tempUniverse; //used for pointer stuffs
	private int leftX, rightX, topY, bottomY;
	private byte current;
	private int total;
	int size;
	private byte newValue;
	private final Color[] colors = new Color[]{Color.BLACK, Color.BLUE, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
	public Application app;
	public final int MIN_SQUARE_SIZE = 2;
	public int actualSH;
	public int actualSW;
	
	public Universe(Application app, int size, int initialBlock) {
		interrupt = false;
		this.app = app;
		currentUniverse = new byte[size][size];
		nextUniverse = new byte[size][size];
		this.size = size;
		System.out.println(Thread.currentThread().getId());
	}
	
	public void paintSquares(Graphics g, int leftX, int topY) {
		
//		System.out.println("Rendering at X: "+ leftX);
//		System.out.println("Rendering at Y " + topY);
		int ph = app.gui.uniPanel.getHeight();
		int pw = app.gui.uniPanel.getWidth();
		//default is height and width of size of panel
		
		
		actualSH = ph / size; // the height of the squares is the height of the panel over the size
		actualSW = pw / size; // the width of the squares is the width of the panel over the size
		
		//if it is less than 2 pixels, we need to set it to 2 pixels
		if (actualSH < MIN_SQUARE_SIZE) {
			actualSH = MIN_SQUARE_SIZE;			
		}
		if (actualSW < MIN_SQUARE_SIZE) {
			actualSW = MIN_SQUARE_SIZE;
		}
		
//		int lx = leftX;
//		int ty = topY;
		
		//we render from the y index to y index + the smaller of(size, numPossibleRectangles)
		//so if height is 1000 and size is 1000
		//rect height is 2
		//y index passed in is 352
		//we render from 352 to 352 + 500
		//if height is 1000 and size is 300
		//rect height is 3
		//passed in 52
		//we render from 52 to 
		for (int j = topY; j < Math.min(size, topY + (ph / actualSH)); ++j) {
			for (int i = leftX; i < Math.min(size, leftX + (pw / actualSW)); ++i) {
				g.setColor(colors[currentUniverse[j][i]]);
				g.fillRect((i - leftX) * actualSH, (j - topY) * actualSW, actualSW, actualSH);
			}
		}
	}
	
	public void render(){
		//do some shit on tempUniverse
	}
	
	public void seedObject(LifeStructure structure, int x, int y) {
		if (structure == LifeStructure.RANDOM) {
			
		}
		//Need to do error checking to ensure that the structure isn't larger than the bounds of the array
		//This is only the case if we're using system.arraycopy
		
		//We can create a little box for creating custom signatures
		
		byte[][] objectSignature = structure.signature;
		
		int height = structure.height;
		int width = structure.width;
		
		int xIndex = x - 1, yIndex = y - 1;
		if (xIndex == -1) {
			xIndex = size - 1;
		}
		if (yIndex == -1) {
			yIndex = size - 1;
		}
		
		
		//need to do the padding
		for (int row = 0; row < height + 2; row++) {
			for (int col = 0; col < width + 2; col++) {
				if (row == 0 || col == 0 || row == height + 1 || col == width + 1) {
					//then we pad it with a zero
					currentUniverse[(row + y) % size][(col + x) % size] = 0;
				} else {
					currentUniverse[(row + y) % size][(col + x) % size] = objectSignature[row - 1][col - 1];
				}
			}
		}
	}
	
	public void logNumLife(int numGens) {
		File file = new File("C://Users/Timothy/Documents/LifeLog/acornLifeCount.dat");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int genNum = 0; //this could be useful to measure the duration of a system
		while (numGens-- != 0) {
			genNum++;
			
			for (int i = 0; i < size; ++i) {
				for (int j = 0; j < size; ++j) {
					update(i,j);
				}
			}
			
			try {
				fw.write(countLife() + ",");
				fw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int countLife() {
		int total = 0;
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				total += currentUniverse[i][j];
			}
		}
		return total;
	}
	
	public void doXGenerations(int numGens) {
		System.out.println(Thread.currentThread().getId());
		int genNum = 0; //this could be useful to measure the duration of a system
		while (numGens-- != 0) {
			if (interrupt) {
				break;
			}
			genNum++;
			
			for (int i = 0; i < size; ++i) {
				for (int j = 0; j < size; ++j) {
					update(i, j);
				}
			}
			
			//at the end of each generation, we need to update the pointers
			//so that current always points to the newest iteration
			tempUniverse = currentUniverse; //store the current in a temp
			currentUniverse = nextUniverse;
			nextUniverse = tempUniverse;
			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			app.gui.uniPanel.repaint();
		}
	}
	
	public void clear() {
		interrupt = true;
	}
	
	
	
	//is this even necessary or should I just return the new value of that square
	private byte update(int y, int x) {
		//need to handle edge cases
		//start with torroidal shape
		total = 0;
		current = currentUniverse[y][x];
		
		//Set boundaries
		leftX = x - 1;
		if (leftX == -1) {
			leftX = size - 1;
		}
		
		rightX = x + 1;
		if (rightX == size) {
			rightX = 0;
		}
		
		topY = y - 1;
		if (topY == -1) {
			topY = size - 1;
		}
		
		bottomY = y + 1;
		if (bottomY == size) {
			bottomY = 0;
		}
		
		
		//Figure out number of adjacent neighbors
		total += currentUniverse[topY][leftX] +
				 currentUniverse[topY][x] +
				 currentUniverse[topY][rightX] +
				 currentUniverse[y][leftX] +
				 currentUniverse[y][rightX] +
				 currentUniverse[bottomY][leftX] +
				 currentUniverse[bottomY][x] +
				 currentUniverse[bottomY][rightX];
		
		switch (total) {
		//why not just store it in directly
		//why use "newValue"
		//might need to do something more sophisticated in the future
			default: newValue = 0;
			break;	
		
			case 3: newValue = 1;
			break;
		
			case 2: newValue = current; //hopefully this doesn't fuck up some pointers
			break;
		}
		
		nextUniverse[y][x] = newValue;
		return newValue;
	}
}
