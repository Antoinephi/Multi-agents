package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environnement;

public abstract class Fish extends Agent {

	protected List<Shark> closeSharks = new ArrayList<Shark>();
	protected List<Tuna> closeTunas = new ArrayList<Tuna>();
	
	public Fish(Environnement env) {
		super(env);
	}
	
	public Fish(int posX, int posY, int dirX, int dirY, Environnement env) {
		super(posX, posY, dirX, dirY, env);
		this.c = new Color(80, 80, 240);
	}
	
	public abstract String getTypeOf();

	public void reproduce(){
		
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
