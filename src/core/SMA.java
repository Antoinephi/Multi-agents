package core;
import hunter.Target;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import particles.Main;

import wator.Shark;
import wator.Tuna;



public class SMA extends Observable {
	
	private Environnement env;
	private List<Agent> agents;
	private int speed;
	private BufferedWriter writer;
	private long init_time;
	private boolean infinite;
	private boolean logging;
	private boolean fairMode;
	
	private int nbSharks;
	private int nbTunas;
	private int nbTargets;
	
	public SMA(int nbAgents, int viewSize, int cellSize, int speed, 
			boolean toric, View v, int nbTurns, boolean infinite, boolean logging, boolean fairMode) throws Exception{
		int envSize = viewSize/cellSize;
		this.infinite = infinite;
		this.logging = logging;
		this.fairMode = fairMode;
		
		env = new Environnement(envSize,envSize, nbAgents, toric);
		this.speed = speed;
		this.addNewAgents();
		agents = env.getAgents();

		this.addObserver(v);
		init_time = System.currentTimeMillis();
		File log = new File("data.log");
		if(logging)
			writer = new BufferedWriter(new FileWriter(log));
		
	}
	
	
	public void run(int nbTurns) throws Exception{
		long time = System.currentTimeMillis();
		this.addNewAgents();

		if(infinite){
			do{
				turn();
				Thread.sleep(this.speed);
			}while(this.nbSharks > 0 && this.nbTunas > 0 || this.nbTargets > 0);

		} else {
			for(int i = 0; i < nbTurns; i++){
	
				turn();
	
				Thread.sleep(this.speed);
			}
		}
		if(logging)
			writer.close();
		System.out.println("Lasted for : " + (System.currentTimeMillis() - init_time) + "ms");
	}
	

	public void turn() throws Exception{
//		System.out.println("Nombre d'agents : " + agents.size());
		long time = System.currentTimeMillis();
		this.nbTunas = 0;
		this.nbSharks = 0;
		this.nbTargets = 0;
		for(Agent a : agents){ // TODO : change to iterator
//			System.out.println((Fish)a.get);
			if(a instanceof Tuna){
				this.nbTunas++;
				if(!((Tuna)a).isAlive())
					this.env.addDeadAgent(a);
//				System.out.println(a);
//				System.out.println(a);
//				a.decide();
			} else if(a instanceof Shark) {
				this.nbSharks++;
				if(!((Shark)a).isAlive())
					this.env.addDeadAgent(a);

//				a.decide();
			} else if(a instanceof Target){
				if(!((Target)a).isAlive())
					this.env.addDeadAgent(a);
				else
					this.nbTargets++;
			}
			a.decide();
		}
		if(logging){
			writer.write((System.currentTimeMillis() - init_time)+";");
			writer.write(agents.size() +";");
			writer.write(nbTunas + ";");
			writer.write(nbSharks + ";\n");
		}
//		System.out.println("1-Nombre d'agents : " + agents.size());
//		System.out.println("\t- Tunas : " + nbTunas);
//		System.out.println("\t- Sharks : " + nbSharks);

		this.removeDeadAgents();
//		System.out.println("2-Nombre d'agents : " + agents.size());

		this.addNewAgents();
//		System.out.println("3-Nombre d'agents : " + agents.size());

		this.setChanged();
		this.notifyObservers();		
		if(fairMode)
			Collections.shuffle(agents);
	}
	
	private void removeDeadAgents(){
//		System.out.println("SIZE : " + this.env.getDeadAgents().size());
//		System.out.println("Nombre d'agents d : " + agents.size());
		this.env.getAgents().removeAll(this.env.getDeadAgents());

		this.env.getDeadAgents().clear();
//		System.out.println("Nombre d'agents f : " + agents.size());

	}
	
	private void addNewAgents(){
		this.env.getAgents().addAll(this.env.getNewAgents());
		this.env.getNewAgents().clear();
	}
	
	public Environnement getEnv(){
		return this.env;
	}
	
	public List<Agent> getAgents(){
		return this.agents;
	}
	
}
