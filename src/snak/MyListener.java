package snak;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyListener implements KeyListener{
	int dir =1;
	MainActivity ma;
	public MyListener(MainActivity ma){
		this.ma=ma;
		//this.ma.dir=this.dir;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch (key){
		case 37:
			dir = 1;
			break;
		case 38:
			dir = 2;
			break;
		case 39:
			dir = 3;
			break;
		case 40:
			dir = 4;
			break;
			default:
				break;
		}
		//System.out.println(dir);
		this.ma.dir=dir;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
