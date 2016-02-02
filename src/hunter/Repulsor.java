package hunter;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Repulsor extends Agent {

	private Target target;
	
	public Repulsor(Environnement env) {
		super(env);
		this.c = new Color(255,0,0);
		this.target = this.env.getChasedAgent();

	}
	
	public Repulsor(int posX, int posY, Environnement env) {
		super(posX, posY, env);
		this.c = new Color(255,0,0);
		this.target = this.env.getChasedAgent();

	}

	@Override
	public void decide() throws Exception {

		this.dirX = r.nextInt(3)-1;
		this.dirY = r.nextInt(3)-1;
		while(!this.env.isAvailable(posX+this.dirX, posY+this.dirY)){
			this.dirX = r.nextInt(3)-1;
			this.dirY = r.nextInt(3)-1;
		}
		if(this.env.isAvailable(posX+this.dirX, posY+this.dirY)){
			this.env.moveAgent(this, posX+this.dirX, posY+this.dirY);
			this.posX = this.env.convertInd(posX+this.dirX);
			this.posY = this.env.convertInd(posY+this.dirY);
			if(this.target != null){
				this.target.getMap()[posX][posY] = -5;
				updateNeibourhood();
				System.out.println("posx " + posX + " posY " + posY);
			}
		}
	}

	public void updateNeibourhood(){
		System.out.println("OK");
		for(int i = posX -1; i < posX+1; i++){
			for(int j = posY -1; j < posY+1; j++){
				if (i >= 0 && j >= 0 && i < this.env.getEnvSize()
						&& j < this.env.getEnvSize()){
//					System.out.println(i+":"+j);
					this.target.getMap()[i][j] += (this.target.getMap()[i][j] != -1 ? 10 : 0);
				}
			}
		}

	}

}
