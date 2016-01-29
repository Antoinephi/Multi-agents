package hunter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener{

	public void keyTyped(KeyEvent e) {
//        System.out.println("Key typed: " + e.getKeyChar());
    }

    public void keyPressed(KeyEvent e) {
//    	Target.KEY_VALUE = 
    	switch(e.getKeyCode()){    	
	    	case 39:
	    		Target.DIR_X = 1;
	    		break;
	    	case 37 :
	    		Target.DIR_X = -1;
	    		break;
	    	case 38:
	    		Target.DIR_Y = -1;
	    		break;
	    	case 40:
	    		Target.DIR_Y = 1;
	    		break;
	    	default:
	    		break;
    	}
//        System.out.println("Key pressed: " + e);
       
    }

    public void keyReleased(KeyEvent e) {
//        System.out.println("Key released: " + e.getKeyChar());
    }

}
