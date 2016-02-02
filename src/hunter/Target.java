package hunter;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Agent;
import core.Environnement;

public class Target extends Agent implements KeyListener {



	public static int[][] map;
	private int dirX;
	private int dirY;


	private boolean isAlive = true;

	public Target(Environnement env) {
		super(env);
		this.c = new Color(237, 255, 74);
		map = new int[this.env.getEnvSize()][this.env.getEnvSize()];

	}

	public Target(int posX, int posY, Environnement env) {
		super(posX, posY, env);
		this.c = new Color(237, 255, 74);
		map = new int[this.env.getEnvSize()][this.env.getEnvSize()];
	}

	@Override
	public void decide() throws Exception {
//		System.out.println("DIRX = " + dirX + " DIRY = " + dirY);
		if(!isAlive)	
			return;
		map = new int[this.env.getEnvSize()][this.env.getEnvSize()];

		this.map[posX][posY] = 0;
		distance(posX, posY);
//		printMap();
//		dirX = 0;
//		dirY = 0;
//		int dirX = r.nextInt(3)-1;
//		int dirY = r.nextInt(3)-1;
//		while(!this.env.isAvailable(posX+dirX, posY+dirY)){
//			dirX = r.nextInt(3)-1;
//			dirY = r.nextInt(3)-1;
//		}
		if(this.env.isAvailable(posX+this.dirX, posY+this.dirY)){
			this.env.moveAgent(this, posX+this.dirX, posY+this.dirY);
			this.posX = this.env.convertInd(posX+this.dirX);
			this.posY = this.env.convertInd(posY+this.dirY);
		}
		
	}
	
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(" " + map[j][i]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public void kill(){
		System.out.println("killed");
		this.isAlive = false;
		this.env.getEspace()[posX][posY] = null;
		this.env.addDeadAgent(this);
	}
	
	public boolean isAlive(){
		return this.isAlive;
	}
	
	public void distance(int x, int y) {
//		System.out.println("call");
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i < 0 || j < 0 || i >= this.env.getEnvSize()
						|| j >= this.env.getEnvSize()){
//					System.out.println("returned" + i+":"+j);
				} else {
					if (this.env.getCell(i, j) == null) {
						if (this.map[i][j] == 0 || this.map[i][j] > this.map[x][y]+1) {
//							System.out.println("updated map");
							this.map[i][j] = map[x][y]+1;
							this.distance(i, j);
						}
					} else if (!(this.env.getCell(i, j) instanceof Target)) {
						this.map[i][j] = -1;
					}
				}
			}
		}
	}
	
	
	public int[][] getMap(){
		return this.map;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {

    	switch(e.getKeyCode()){    	
    	case 39:
    		this.dirX = 1;
    		break;
    	case 37 :
    		this.dirX = -1;
    		break;
    	case 38:
    		this.dirY = -1;
    		break;
    	case 40:
    		this.dirY = 1;
    		break;
    	default:
    		break;
	}

	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){    	
    	case 39:
    		this.dirX = 0;
    		break;
    	case 37 :
    		this.dirX = 0;
    		break;
    	case 38:
    		this.dirY = 0;
    		break;
    	case 40:
    		this.dirY = 0;
    		break;
    	default:
    		break;
		}
	}
}
