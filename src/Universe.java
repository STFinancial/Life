
public class Universe {
	public byte[][] currentUniverse;
	public byte[][] nextUniverse;
	public byte[][] tempUniverse;
	public byte[][] tempUniverse2;
	private int leftX, rightX, topY, bottomY;
	private byte current;
	private boolean whichUniverse;
	private int total;
	private int size;
	private byte newValue;
	
	public Universe(int size, int initialBlocks) {
		currentUniverse = new byte[size][size];
		nextUniverse = new byte[size][size];
		whichUniverse = true;
		this.size = size;
	}
	
	public void render(){
		//do some shit on tempUniverse
		
	}
	
	public void doXGenerations(int numGens) {
		while (numGens != 0) {
			if (whichUniverse) {
				tempUniverse = currentUniverse;
				tempUniverse2 = nextUniverse;
			} else {
				tempUniverse = nextUniverse;
				tempUniverse2 = currentUniverse;
			}
			whichUniverse = !whichUniverse;
			
			for (int i = 0; i < size; ++i) {
				for (int j = 0; j < size; ++j) {
					update(i, j);
				}
			}
		}
	}
	
	//is this even necessary or should I just return the new value of that square
	private void update(int y, int x) {
		//need to handle edge cases
		//start with torroidal shape
		total = 0;
		current = tempUniverse[y][x];
		
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
		
		total += tempUniverse[topY][leftX] +
				 tempUniverse[topY][x] +
				 tempUniverse[topY][rightX] +
				 tempUniverse[y][leftX] +
				 tempUniverse[y][rightX] +
				 tempUniverse[bottomY][leftX] +
				 tempUniverse[bottomY][x] +
				 tempUniverse[bottomY][rightX];
		
		switch (total) {
			case 3: newValue = 1;
			break;
		
			case 2: newValue = current; //hopefully this doesn't fuck up some pointers
			break;
		
			default: newValue = 0;
			break;
		}
		
		tempUniverse2[y][x] = newValue;
	}
}
