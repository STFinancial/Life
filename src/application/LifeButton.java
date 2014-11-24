package application;

import javax.swing.JButton;

public class LifeButton extends JButton {
	static int counter = 0;
	int ID;
	
	public LifeButton() {
		super();
		ID = counter++;
	}
	
	public LifeButton(String text) {
		super(text);
		ID = counter++;
	}
	
	boolean equals(LifeButton b) {
		return b.ID == ID;
	}

}
