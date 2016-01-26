package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environnement;

public abstract class Fish extends Agent {

	protected List<Shark> closeSharks = new ArrayList<Shark>();
	protected List<Tuna> closeTunas = new ArrayList<Tuna>();
	protected static int INIT_BREED;
	protected int nbBreed;
	protected int age;
	
	public Fish(Environnement env, int nbBreed) {
		super(env);
		this.nbBreed = nbBreed;
		INIT_BREED = nbBreed;
	}
	
	public Fish(int posX, int posY, int dirX, int dirY, Environnement env, int nbBreed) {
		super(posX, posY, dirX, dirY, env);
		this.c = new Color(80, 80, 240);
		this.nbBreed = nbBreed;
		INIT_BREED = nbBreed;
	}
	
	public abstract String getTypeOf();

	protected void reproduce(int x, int y, Fish f){
		if(this.env.getCell(x, y) != null)
			throw new IllegalAccessError("Cannot create Fish in non-empty cell");
		this.env.getEspace()[x][y] = f;
		this.nbBreed = INIT_BREED;
	}
	
	public void getCurrentNeighbourhood(){
		closeSharks = new ArrayList<Shark>();
		closeTunas = new ArrayList<Tuna>();
		for(Agent[] agents : this.env.getLocalEnv(posX, posY)){
			for(Agent a : agents){
				if(a instanceof Shark){
					closeSharks.add((Shark) a);
				} else if (a instanceof Tuna){
					closeTunas.add((Tuna) a);
				}
			}
		}
		
	}




}
