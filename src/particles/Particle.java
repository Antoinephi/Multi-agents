package particles;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Particle extends Agent{
	
	protected int dirX;
	protected int dirY;
	
	public Particle(int posX, int posY, int dirX, int dirY, Environnement env) {
		super(posX, posY, env);
		this.dirX = dirX;
		this.dirY = dirY;
		this.c = new Color(r.nextInt(230), r.nextInt(230), r.nextInt(230));

	}
	
	public Particle(Environnement env){
		super(env);
		this.c = new Color(r.nextInt(230), r.nextInt(230), r.nextInt(230));

	}

	public void decide() throws Exception{

		int newX = posX+dirX;
		int newY = posY+dirY;
		int oldX = this.posX;
		int oldY = this.posY;
		
		if(dirX == 0 && dirY == 0)
			return;
		
		if(env.isToric()){
			newX = env.convertToToric(newX);
			newY = env.convertToToric(newY);
			oldX = env.convertToToric(oldX);
			oldY = env.convertToToric(oldY);
		}
		if(this.env.isAvailable(newX, newY)){
			this.env.moveAgent(this, newX, newY);
			this.posX = newX;
			this.posY = newY;
		} else {
			if(newX < this.env.getEnvSize() && newX >= 0 && newY < this.env.getEnvSize() && newY >=0){
				this.dirX = -1*this.dirX;
				this.dirY = -1*this.dirY;
				Agent agent = this.env.getCell(newX, newY);
				agent.setDirX(agent.getDirX() == 0 ? -1*this.getDirX() : -1*agent.getDirX());
				agent.setDirY(agent.getDirY() == 0 ? -1*this.getDirY() : -1*agent.getDirY());
			} else if(!this.env.isToric()){
				if(newX >= this.env.getEnvSize() || newX <= 1)
					this.dirX = -1*this.dirX;
				if(newY >= this.env.getEnvSize() || newY <= 1)
					this.dirY = -1*this.dirY;
			}
		}
		
	}

	
	
}
