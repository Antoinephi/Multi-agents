package wator;

import java.awt.Color;
import java.util.List;

import core.Agent;
import core.Environnement;

public class Shark extends Fish {
	
	private static int INIT_HUNGER;

	private int hunger;
	private boolean isAlive = true;
	
	
	public Shark(int posX, int posY, Environnement env, int nbBreed, int hunger) {
		super(posX, posY, env, nbBreed);
		this.c = new Color(80, 80, 240);
		INIT_HUNGER = hunger;
		this.hunger = hunger;
	}
	
	public Shark(Environnement env, int nbBreed, int hunger) {
		super(env, nbBreed);
		this.c = new Color(80, 80, 240);
		INIT_HUNGER = hunger;
		this.hunger = hunger;
	}

	public void decide() throws Exception {
		this.updateParameters();
//		System.out.println(hunger);
		if(hunger <= 0) {
			isAlive = false;
			this.env.addDeadAgent(this);
			this.env.getEspace()[this.posX][this.posY] = null;
			return;
		} 
		this.getCurrentNeighbourhood();
		//TODO : find closest cell (fish, shark, empty)
//		System.out.println(nearbyFish.size());
		System.out.println(nbBreed);
		if(nbBreed <= 0 && closeSharks.size() + closeTunas.size() < 9){
			this.reproduce(this.posX+1, this.posY, new Shark(this.posX+1, this.posY, this.env, INIT_HUNGER, INIT_BREED));
			
		} else if(closeTunas.size() > 0){
			this.eatFish(closeTunas.get(0).getPosX(), closeTunas.get(0).getPosY());
		}
		
	}
	
	protected void updateParameters(){
		super.updateParameters();
		this.hunger--;
//		System.out.println(this + " hunger = " + hunger);
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
