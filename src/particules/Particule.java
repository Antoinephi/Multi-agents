package particules;

import core.Agent;
import core.Environnement;

public class Particule extends Agent{

	public Particule(int posX, int posY, int dirX, int dirY, Environnement env) {
		super(posX, posY, dirX, dirY, env);
	}
	
	public Particule(Environnement env){
		super(env);
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
//			System.out.println("newX : " + newX + "newY : " + newY);
			this.env.moveAgent(this, newX, newY);
			this.posX = newX;
			this.posY = newY;
		} else {
			if(newX < this.env.getEnvSize() && newX >= 0 && newY < this.env.getEnvSize() && newY >=0){
				this.dirX = -1*this.dirX;
				this.dirY = -1*this.dirY;
				Agent agent = this.env.getCell(newX, newY);
				agent.setDirX(-1*agent.getDirX());
				agent.setDirY(-1*agent.getDirY());
				if(this.env.isToric()){
//					this.env.update
//					this.espace[x][y].setDirX(this.espace[x][y].getDirX() == 0 ? -1*agent.getDirX() : -1*this.espace[x][y].getDirX());
//					this.espace[x][y].setDirY(this.espace[x][y].getDirY() == 0 ? -1*agent.getDirY() : -1*this.espace[x][y].getDirY());
				}
			} else {
				if(newX >= this.env.getEnvSize() || newX <= 1)
					this.dirX = -1*this.dirX;
				if(newY >= this.env.getEnvSize() || newY <= 1)
					this.dirY = -1*this.dirY;
			}
		}
		
	}

	
	
}
