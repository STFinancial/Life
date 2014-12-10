package application;

public abstract interface GenericUniverse {
	
	public abstract void render();
	public abstract void doXGenerations(int numGens);
	public abstract void runIndefinitely();
	
	public abstract void zoomIn(int zoomFactor);
	public abstract void zoomOut(int zoomFactor);
	
	public abstract void increaseSpeed();
	public abstract void decreaseSpeed();
}
