package hunter;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Target extends Agent{
	
	public static int DIR_X;
	public static int DIR_Y;

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
		if(this.env.isAvailable(posX+DIR_X, posY+DIR_Y)){
			this.env.moveAgent(this, posX+DIR_X, posY+DIR_Y);
			this.posX = this.env.convertInd(posX+DIR_X);
			this.posY = this.env.convertInd(posY+DIR_Y);
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
}
