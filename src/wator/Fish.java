package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.Agent;
import core.Environnement;

public abstract class Fish extends Agent {

	protected List<Shark> closeSharks;
	protected List<Tuna> closeTunas;
	protected List<int[]> nearbyEmptyCells;
	protected static int INIT_BREED;
	protected int nbBreed;
	protected int age;
	protected boolean isAlive = true;
	
	public Fish(Environnement env, int nbBreed) {
		super(env);
		this.nbBreed = nbBreed;
		INIT_BREED = nbBreed;
	}
	
	public Fish(int posX, int posY, Environnement env, int nbBreed) {
		super(posX, posY, env);
		this.c = new Color(80, 80, 240);
		this.nbBreed = nbBreed;
		INIT_BREED = nbBreed;
	}
	
	public abstract String getTypeOf();

	protected void reproduce(Fish f){
		this.nbBreed = INIT_BREED;
	}
	
	protected void updateParameters(){
		this.age++;
		this.nbBreed--;
	}
	
	protected void move(int x, int y){
		if(this.env.moveAgent(this, x, y)){
			this.posX = this.env.convertInd(x);
			this.posY = this.env.convertInd(y);
		}
	}
	
	public void getCurrentNeighbourhood(){
		nearbyEmptyCells = new ArrayList<int[]>();
		closeSharks = new ArrayList<Shark>();
		closeTunas = new ArrayList<Tuna>();
		Agent a;
		for(int i = this.posX-1; i <= this.posX+1; i++){
			for(int j = this.posY-1; j <= this.posY+1; j++){
				a = this.env.getCell(i, j);
				if(a == null && this.env.convertInd(i) != -1 && this.env.convertInd(j) != -1){
					int[] tab = {this.env.convertInd(i),this.env.convertInd(j)};
					nearbyEmptyCells.add(tab);
				} else if(a instanceof Tuna){
					closeTunas.add((Tuna)a);
				} else if(a instanceof Shark){
					closeSharks.add((Shark)a);
				} 
			}
		}
		Collections.shuffle(nearbyEmptyCells);
		Collections.shuffle(closeSharks);
		Collections.shuffle(closeTunas);

	}
	
	public void kill(){
		this.isAlive = false;
		this.env.addDeadAgent(this);
		if(this.env.getCell(posX, posY) == this)
			this.env.setAgent(posX, posY, this);
	}

	public boolean isAlive(){
		return this.isAlive;
	}


}
