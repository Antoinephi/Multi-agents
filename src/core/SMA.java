package core;
import hunter.Hunter;
import hunter.Target;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import wator.Shark;
import wator.Tuna;



public class SMA extends Observable {
	
	private Environnement env;
	private List<Agent> agents;
	private int simSpeed;
	private BufferedWriter writer;
	private long init_time;
	private boolean infinite;
	private boolean logging;
	private boolean fairMode;
	
	private int nbSharks;
	private int nbTunas;
	private int nbTargets;
	private int agentSpeed;
	
	private boolean particles = false;
	
	public SMA(int nbAgents, int viewSize, int cellSize, int speed, 
			boolean toric, View v, int nbTurns, boolean infinite, boolean logging, boolean fairMode, int agentSpeed) throws Exception{
		int envSize = viewSize/cellSize;
		this.infinite = infinite;
		this.logging = logging;
		this.fairMode = fairMode;
		
		env = new Environnement(envSize,envSize, nbAgents, toric);
		this.simSpeed = speed;
		this.agentSpeed = agentSpeed;
		
		this.addNewAgents();
		agents = env.getAgents();

		this.addObserver(v);
		init_time = System.currentTimeMillis();
		File log = new File("data.log");
		if(logging)
			writer = new BufferedWriter(new FileWriter(log));
		
	}
		
	public void run(int nbTurns) throws Exception{
		this.addNewAgents();

		if(infinite){
			do{
				turn();
				Thread.sleep(this.simSpeed);
			}while(this.nbSharks > 0 && this.nbTunas > 0 || this.nbTargets > 0 || this.particles);

		} else {
			for(int i = 0; i < nbTurns; i++){
				turn();
				Thread.sleep(this.simSpeed);
			}
		}
		if(logging)
			writer.close();
		System.out.println("Lasted for : " + (System.currentTimeMillis() - init_time) + "ms");
	}
	

	public void turn() throws Exception{
		this.nbTunas = 0;
		this.nbSharks = 0;
		this.nbTargets = 0;
		for(Agent a : agents){
			if(a instanceof Tuna){
				this.nbTunas++;
				if(!((Tuna)a).isAlive())
					this.env.addDeadAgent(a);
			} else if(a instanceof Shark) {
				this.nbSharks++;
				if(!((Shark)a).isAlive())
					this.env.addDeadAgent(a);
			} else if(a instanceof Target){
				if(!((Target)a).isAlive())
					this.env.addDeadAgent(a);
				else
					this.nbTargets++;
			} else if(a instanceof Hunter){
				for(int i = 0; i <agentSpeed-1; i++){
					a.decide();
				}
			}
			a.decide();
		}
		if(logging){
			writer.write((System.currentTimeMillis() - init_time)+";");
			writer.write(agents.size() +";");
			writer.write(nbTunas + ";");
			writer.write(nbSharks + ";\n");
		}

		this.removeDeadAgents();
		this.addNewAgents();
		
		this.setChanged();
		this.notifyObservers();		
		if(fairMode)
			Collections.shuffle(agents);
	}
	
	private void removeDeadAgents(){
		this.env.getAgents().removeAll(this.env.getDeadAgents());
		this.env.getDeadAgents().clear();
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
	
	public void setParticleMode(){
		this.particles = true;
	}
	
}
