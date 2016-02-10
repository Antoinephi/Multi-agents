package core;
import hunter.Target;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Environnement {
	
	private Agent[][] espace;
	private List<Agent> agents;
	private List<Agent> deadAgents;
	private List<Agent> newAgents;
	
	private boolean toric = false;
	
	public Environnement(int sizeX, int sizeY,int nbAgents, boolean toric) throws Exception{
		this.espace = new Agent[sizeX][sizeY];
		this.agents = new LinkedList<Agent>();
		this.deadAgents = new ArrayList<Agent>();
		this.newAgents = new ArrayList<Agent>();
		if(nbAgents > sizeX * sizeY) throw new Exception("Reduce agents number or increase environnement's size !");

		this.toric = toric;
		
	}
			
	public boolean isAvailable(int x, int y) {
		if(convertInd(x) !=-1 && convertInd(y) != -1)
			return this.espace[convertInd(x)][convertInd(y)] == null;
		return false;
	}

	public Agent[][] getEspace() {
		return espace;
	}
	
	public void setAgent(int x, int y, Agent a){
		if(this.toric)
			this.espace[(x+this.espace.length)%this.espace.length][(y+this.espace.length)%this.espace.length] = a;
		else if(!this.toric && x < this.espace.length && y < this.espace.length && x >= 0 && y >= 0)
			this.espace[x][y] = a;
	}
	
	public void addAgent(Agent a) throws Exception{
		if(this.espace[a.getPosX()][a.getPosY()] != null)
			throw new Exception("Error : cannot place new agent on top of another one !");
		this.espace[a.getPosX()][a.getPosY()] = a;
		this.newAgents.add(a);
	}
	
	public List<Agent> getAgents(){
		return this.agents;
	}
	
	public boolean isToric() {
		return this.toric;
	}
	
	public int getEnvSize(){
		return this.espace.length;
	}
	
	public Agent getCell(int x, int y){
		if(convertInd(x)!= -1 && convertInd(y) != -1){
			return this.espace[convertInd(x)][convertInd(y)];
		} else
			return null;
		
	}
	
	public int convertToToric(int val){
		return (val+this.espace.length)%this.espace.length;
	}

	public boolean moveAgent(Agent a, int x, int y) {		
		if(convertInd(x) != -1 && convertInd(y) != -1){
			this.espace[a.getPosX()][a.getPosY()] = null;
			this.espace[convertInd(x)][convertInd(y)] = a;
			return true;
		}
			
		
		return false;
	}
	

	
	public void addDeadAgent(Agent a){
		this.deadAgents.add(a);
	}
	
	public List<Agent> getDeadAgents(){
		return this.deadAgents;
	}
	
	public void addNewAgent(Agent a){
		this.newAgents.add(a);
	}
	
	public List<Agent> getNewAgents(){
		return this.newAgents;
	}
	
	public int convertInd(int i){
		if(this.toric)
			return (i+this.espace.length)%this.espace.length;
		else if(i < this.espace.length && i >= 0)
			return i;
		else
			return -1;
			
	}

	public Target getChasedAgent() {
		for(Agent a : agents)
			if(a instanceof Target){
				return (Target) a;
			}
		return null;
	}
	

	
}
