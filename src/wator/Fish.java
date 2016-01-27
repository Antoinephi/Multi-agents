package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environnement;

public abstract class Fish extends Agent {

	protected List<Shark> closeSharks = new ArrayList<Shark>();
	protected List<Tuna> closeTunas = new ArrayList<Tuna>();
	protected List<int[]> nearbyEmptyCells = new ArrayList<int[]>();
	protected static int INIT_BREED;
	protected int nbBreed;
	protected int age;
	
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

	protected void reproduce(int x, int y, Fish f){
		System.out.println(">>>>>>>reproduce");
		System.out.println("x  : "  + x + " y : " + y);
//			throw new IllegalAccessError("Cannot create Fish in non-empty cell");
		this.env.getEspace()[x][y] = f;
		this.env.addNewAgent(f);
		this.nbBreed = INIT_BREED;
	}
	
	protected void updateParameters(){
		this.age++;
		this.nbBreed--;
	}
	
	public void getCurrentNeighbourhood(){
		nearbyEmptyCells = new ArrayList<int[]>();
		closeSharks = new ArrayList<Shark>();
		closeTunas = new ArrayList<Tuna>();
		Agent a;
		for(int i = this.posX-1; i < this.posX+1; i++){
			for(int j = this.posY-1; j < this.posY; j++){
				a = this.env.getCell(i, j);
				if(a == null && i >= 0 && i < this.env.getEnvSize() && j >=0 && j >= this.env.getEnvSize()){
					int[] tab = {i,j};
					nearbyEmptyCells.add(tab);
				} else if(a instanceof Tuna){
					closeTunas.add((Tuna)a);
				} else if(a instanceof Shark){
					closeSharks.add((Shark)a);
				}
			}
		}
	}



}
