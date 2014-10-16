package items;

import items.LifeStructure;

public enum LifeStructure {
	//Assume all structures are without padding
	
	ACORN(3, 7, new byte[][]{{0,1,0,0,0,0,0}, {0,0,0,1,0,0,0}, {1,1,0,0,1,1,1}}),
	RANDOM(0, 0, null);
	
	public int height, width;
	public byte[][] signature;
	
	private LifeStructure(int height, int width, byte[][] signature) {
		this.height = height;
		this.width = width;
		this.signature = signature;
	}
}
