package hunter;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Target extends Agent{

	private int dirX;
	private int dirY;

	private boolean isAlive = true;

	public Target(Environnement env) {
		super(env);
		this.c = new Color(237, 255, 74);
	}

	public Target(int posX, int posY, Environnement env) {
		super(posX, posY, env);
		this.c = new Color(237, 255, 74);
	}

	@Override
	public void decide() throws Exception {
		System.out.println("DIRX = " + dirX + " DIRY = " + dirY);
		if(!isAlive)	
			return;
//		if(this.env.isAvailable(posX+1, posY)){
//			this.env.moveAgent(this, this.posX+1, this.posY);
//			this.posX = this.env.convertInd(posX+1);
//			this.posY = this.env.convertInd(posY);
//		}
//		int dirX = r.nextInt(3)-1;
//		int dirY = r.nextInt(3)-1;
//		while(!this.env.isAvailable(posX+dirX, posY+dirY)){
//			dirX = r.nextInt(3)-1;
//			dirY = r.nextInt(3)-1;
//		}
		if(this.env.isAvailable(posX+dirX, posY+dirY)){
			this.env.moveAgent(this, posX+dirX, posY+dirY);
			this.posX = this.env.convertInd(posX+dirX);
			this.posY = this.env.convertInd(posY+dirY);
		}
		
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
	
	public void changeDir(int dirX, int dirY){
		this.dirX = dirX;
		this.dirY = dirY;	
	}
}
