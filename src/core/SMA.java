package core;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import particles.ParticlesView;



public class SMA extends Observable {
	
	private Environnement env;
	private static List<Agent> agents;
	private int speed;
	
	public SMA(int nbAgents, int viewSize, int cellSize, int speed, boolean toric, View v, int nbTurns) throws Exception{
		int envSize = viewSize/cellSize;

		env = new Environnement(envSize,envSize, nbAgents, toric);
		this.speed = speed;
//		View v = new ParticulesView(viewSize,cellSize, "Billes");
		agents = env.getAgents();
		this.addObserver(v);
	}
	
	
	public void run(int nbTurns) throws Exception{
		for(int i = 0; i < nbTurns; i++){
			turn();
			Thread.sleep(this.speed);
		}
	}
	

	public void turn() throws Exception{
//		System.out.println("Nombre d'agents : " + agents.size());
		
		for(Agent a : agents){ // TODO : change to iterator
			a.decide();
		}

		this.setChanged();
		this.notifyObservers();		
		Collections.shuffle(agents);
	}
	
	public Environnement getEnv(){
		return this.env;
	}
	
	public List<Agent> getAgents(){
		return this.agents;
	}
	
}
