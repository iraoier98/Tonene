package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import testing.Loop;

public class Keyboard extends KeyAdapter{
	
	private boolean pressed;
	private char lastchar;
	
	public Keyboard(){
		super();
		this.pressed = false;
		this.lastchar = ' ';
	}

	@Override
	public void keyPressed(KeyEvent keyEvent){
		char c = keyEvent.getKeyChar();
		int keyCode = keyEvent.getKeyCode();
		if (keyCode == KeyEvent.VK_BACK_SPACE){
			Loop.registerKey((char)1000);	//Equivalent to deleting
		} else if (keyCode == KeyEvent.VK_ENTER){
			Loop.registerKey((char)1001);	//Equivalent to enter
		} else if ((!pressed || (c != lastchar)) && c <= 126 && c >= 32){	//Check it is not a special character
			Loop.registerKey(c);
			lastchar = c;
			pressed = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		pressed = false;
	}

	public boolean isPressed() {
		return pressed;
	}
	
	
}
