package wator;

import java.awt.Color;
import java.util.List;

import core.Agent;
import core.Environnement;

public class Shark extends Fish {
	
	private static int INIT_HUNGER;

	private int hunger;
	private boolean isAlive = true;
	
	
	public Shark(int posX, int posY, int dirX, int dirY, Environnement env, int nbBreed, int hunger) {
		super(posX, posY, dirX, dirY, env, nbBreed);
		this.c = new Color(80, 80, 240);
		INIT_HUNGER = hunger;
	}
	
	public Shark(Environnement env, int nbBreed, int hunger) {
		super(env, nbBreed);
		this.c = new Color(80, 80, 240);
		INIT_HUNGER = hunger;
	}

	public void decide() throws Exception {
		if(hunger == 0) {
			isAlive = false;
			return;
		} 
		
		List<Agent> nearbyFish  = this.env.getNeighboursList(this.posX,this.posY);
		//TODO : find closest cell (fish, shark, empty)
		
		if(nbBreed == 0 && nearbyFish.size() < 9){
//			this.reproduce(x, y, new Shark(this.env, INIT_HUNGER, INIT_BREED));
			
		}
		
		for(Agent f : nearbyFish){
			if(f instanceof Tuna){
				this.eatFish(f.getPosX(), f.getPosY());
			} else if(f != null) {
				
			}
		}
	}

	private void eatFish(int x, int y){
		this.env.getEspace()[x][y] = this;
		this.posX = x;
		this.posY = y;
		this.hunger = INIT_HUNGER;
	}
	
	public String getTypeOf() {
		return "shark";
	}


}
