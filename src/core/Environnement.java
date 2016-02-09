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
	
	private static int MAX_AGENT;
	private boolean toric = false;
	
	public Environnement(int sizeX, int sizeY,int nbAgents, boolean toric) throws Exception{
		this.espace = new Agent[sizeX][sizeY];
		this.agents = new LinkedList<Agent>();
		this.deadAgents = new ArrayList<Agent>();
		this.newAgents = new ArrayList<Agent>();
		if(nbAgents > sizeX * sizeY) throw new Exception("Reduce agents number or increase environnement's size !");
		MAX_AGENT = nbAgents;

		this.toric = toric;
		
	}
			
	public List<Agent> getNeighboursList(int x, int y){
		List<Agent> localEnv = new ArrayList<Agent>();
		int newX = x;
		int newY = y;

		
		for(int i = -1; i < 1; i++){
			for(int j = -1; j < 1; j++){
				if(this.toric){
					newX = (x+i+this.espace.length)%this.espace.length;
					newY = (j+y+this.espace.length)%this.espace.length;
					localEnv.add(this.espace[i+x][j+y]);
				} else if(!this.toric && (x+i >= this.espace.length || y+j >= this.espace.length || x+i < 0 || y+j < 0)) {
					localEnv.add(null);
				} else {
					localEnv.add(this.espace[newX+i][newY+j]);
				
				}
			}
		}
		return localEnv;
	}

	public boolean isAvailable(int x, int y) {
//		if(this.toric){
//			x = (x+this.espace.length)%this.espace.length;
//			y = (y+this.espace.length)%this.espace.length;
//		}
//		if(x < this.espace.length && x >= 0 && y < this.espace.length && y >=0){
//			System.out.println("OUI");
//
//			return this.espace[x][y] == null;
//		}
//		System.out.println("NON");
//		return false;
		if(convertInd(x) !=-1 && convertInd(y) != -1)
			return this.espace[convertInd(x)][convertInd(y)] == null;
		return false;
	}

		public void print(){
		for(int i = 0; i < espace.length; i++ ){
			System.out.println();
			for(int j = 0; j < espace.length; j++){
				if(espace[i][j] == null)
					System.out.print("  ");
				else
					System.out.print(this.agents.indexOf(espace[i][j]));
			}
		}
		System.out.println();
	}

	public Agent[][] getEspace() {
		return espace;
	}
	
	public void setAgent(int x, int y, Agent a){
		if(this.toric)
			this.espace[(x+this.espace.length)%this.espace.length][(y+this.espace.length)%this.espace.length] = a;
		else if(!this.toric && x < this.espace.length && y < this.espace.length && x >= 0 && y >= 0)
			this.espace[x][y] = a;
		else
			System.out.println("ERROR !!!!");
	}
	
	public void addAgent(Agent a) throws Exception{
		if(this.espace[a.getPosX()][a.getPosY()] != null)
//			System.out.println(a.getPosX() + " >>>> " + a.getPosY());
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
//		System.out.println("x  : "  + x + " y : " + y);
//		if(this.toric)
//			return this.espace[(x+this.espace.length)%this.espace.length][(y+this.espace.length)%this.espace.length];
//		else if(!this.toric && x < this.espace.length && y < this.espace.length && x >= 0 && y >= 0)
//			return this.espace[x][y];
//		else
//			return null;
		if(convertInd(x)!= -1 && convertInd(y) != -1){
//			System.out.println(this.espace[convertInd(x)][convertInd(y)]);
			return this.espace[convertInd(x)][convertInd(y)];
		} else
			return null;
		
	}
	
	public int convertToToric(int val){
		return (val+this.espace.length)%this.espace.length;
	}

	public boolean moveAgent(Agent a, int x, int y) {
//		if(this.espace[(x+this.espace.length)%this.espace.length][(y+this.espace.length)%this.espace.length] == null){
//			this.espace[a.getPosX()][a.getPosY()] = null;
//			this.espace[(x+this.espace.length)%this.espace.length][(y+this.espace.length)%this.espace.length] = a;
//			return true;
//		}
		
		if(convertInd(x) != -1 && convertInd(y) != -1){
			this.espace[a.getPosX()][a.getPosY()] = null;
			this.espace[convertInd(x)][convertInd(y)] = a;
			return true;
		}
			
		
		return false;
	}
	
	public boolean changeAgentDir(Agent a, int x, int y, int dirX, int dirY){
		if(this.espace[x][y] != null){
			this.espace[x][y].setDirX(this.espace[x][y].getDirX() == 0 ? -1*a.getDirX() : -1*this.espace[x][y].getDirX());
			this.espace[x][y].setDirY(this.espace[x][y].getDirY() == 0 ? -1*a.getDirY() : -1*this.espace[x][y].getDirY());
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
//				System.out.println("Target : " + a.getPosX() + ":" +a.getPosY());
				return (Target) a;
			}
		return null;
	}
	

	
}
