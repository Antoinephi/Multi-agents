package core;
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
	private int speed;
	private BufferedWriter writer;
	private long init_time;
	
	public SMA(int nbAgents, int viewSize, int cellSize, int speed, boolean toric, View v, int nbTurns) throws Exception{
		int envSize = viewSize/cellSize;

		env = new Environnement(envSize,envSize, nbAgents, toric);
		this.speed = speed;
//		View v = new ParticulesView(viewSize,cellSize, "Billes");
//		this.addNewAgents();
		agents = env.getAgents();
		this.addObserver(v);
		init_time = System.currentTimeMillis();
		File log = new File("data.log");
		writer = new BufferedWriter(new FileWriter(log));
		
	}
	
	
	public void run(int nbTurns) throws Exception{
		for(int i = 0; i < nbTurns; i++){
			long startTime = System.currentTimeMillis();

			turn();

			long endTime = System.currentTimeMillis();

//			System.out.println("That took " + (endTime - startTime) + " milliseconds");
			Thread.sleep(this.speed);
		}
		writer.close();
	}
	

	public void turn() throws Exception{
//		System.out.println("Nombre d'agents : " + agents.size());
		long time = System.currentTimeMillis();
		int nbTunas = 0;
		int nbSharks = 0;
		for(Agent a : agents){ // TODO : change to iterator
//			System.out.println((Fish)a.get);
			if(a instanceof Tuna){
				nbTunas++;
				if(!((Tuna)a).isAlive())
					this.env.addDeadAgent(a);
//				System.out.println(a);
//				System.out.println(a);
//				a.decide();
			} else if(a instanceof Shark) {
				nbSharks++;
				if(!((Shark)a).isAlive())
					this.env.addDeadAgent(a);

//				a.decide();
			}
			a.decide();
		}
		writer.write((System.currentTimeMillis() - init_time)+";");
		writer.write(agents.size() +";");
		writer.write(nbTunas + ";");
		writer.write(nbSharks + ";\n");
//		System.out.println("\t- Tunas : " + nbTunas);
//		System.out.println("\t- Sharks : " + nbSharks);
//		System.out.println("1-Nombre d'agents : " + agents.size());

		this.removeDeadAgents();
//		System.out.println("2-Nombre d'agents : " + agents.size());

		this.addNewAgents();
//		System.out.println("3-Nombre d'agents : " + agents.size());

		this.setChanged();
		this.notifyObservers();		
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
