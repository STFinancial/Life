package application;
import items.LifeStructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.SwingUtilities;


public class Application {
	
	public final String LOG_PATH = "C://Users/Timothy/Documents/LifeLog/baselineData.dat";
	public final String FRAME_ICON_PATH = "";
	LifeGUI gui;
	Universe uni;
	public final int UNIVERSE_SIZE = 200;
	public final int INITIAL_BLOCKS = 0;
	
	public static void main(String[] args) {
		Application app = new Application();
		app.initialize();
		
	}
	
	public void initialize() {
		uni = new Universe(this, UNIVERSE_SIZE, INITIAL_BLOCKS);
		gui = new LifeGUI(uni, this);
		uni.seedObject(LifeStructure.ACORN, 0, 0);
		
		gui.panel.repaint();
		uni.doXGenerations(20000);
	}
	
	
	
	/////////////////////////////////////////////////
	
	public void monitorLifeCount(int size) {
		uni.seedObject(LifeStructure.ACORN, 0, 0);
		uni.logNumLife(10000);
	}
	
//	public void testVariousSizes(int startSize, int endSize){
//		if (startSize < 10) {
//			startSize = 10;
//		}
//		if (endSize > 2000) {
//			endSize = 2000;
//		}
//		
//		File file = new File(LOG_PATH);
//		FileWriter fw = null;
//		try {
//			fw = new FileWriter(file, false);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		long startTime, endTime;
//		long timeTaken;
//		for (int size = startSize; size < endSize; size++) {
//			uni = new Universe(size, 0);
//			if (size % 500 == 0) {
//				System.out.println("Size: " + size);
//			}
//			startTime = System.nanoTime();
//			uni.seedObject(LifeStructure.ACORN, 0, 0);
//			uni.doXGenerations(1000);
//			endTime = System.nanoTime();
//			timeTaken = endTime - startTime;
//			System.out.println("Size: " + size + " : " + timeTaken);
//			try {
//				fw.write(timeTaken + ",");
//				fw.flush();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		try {
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}
