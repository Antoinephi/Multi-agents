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

		if(env.isToric()){
			if(env.update(this, ((posX+dirX)+env.getEspace().length)%env.getEspace().length, ((posY+dirY)+env.getEspace().length)%env.getEspace().length)){
				this.posX = ((posX+dirX)+env.getEspace().length)%env.getEspace().length;	
				this.posY = ((posY+dirY)+env.getEspace().length)%env.getEspace().length;
			}
		} else {
			if(env.update(this, posX+dirX, posY+dirY)){
				this.posX +=dirX;
				this.posY +=dirY;
			}
		}
	}

	
	
}
