package core;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import particles.Particle;


public class Environnement {
	
	private Agent[][] espace;
	private List<Agent> agents;
	private static int MAX_AGENT;
	private boolean toric = false;
	Random r = new Random();
	
	public Environnement(int sizeX, int sizeY,int nbAgents, boolean toric) throws Exception{
		this.espace = new Agent[sizeX][sizeY];
		agents = new LinkedList<Agent>();

		if(nbAgents > sizeX * sizeY) throw new Exception("Reduce agents number or increase environnement's size !");
		MAX_AGENT = nbAgents;

		this.toric = toric;
		
	}
		
	public Agent[][] getLocalEnv(int x, int y){
		Agent[][] localEnv = new Agent[3][3];
		int newX = x;
		int newY = y;
		for(int i = -1; i < localEnv.length-1; i++){
			for(int j = -1; j < localEnv.length-1; j++){
				//System.out.println("x+i : " + (x+i) + " y+j : " + (y+j));
				if(this.toric){
					newX = (x+i+this.espace.length)%this.espace.length;
					newY = (j+y+this.espace.length)%this.espace.length;
					localEnv[i+1][j+1] = this.espace[newX][newY]; 
				} else if(!this.toric && (x+i >= this.espace.length || y+j >= this.espace.length || x+i < 0 || y+j < 0)) {
						localEnv[i+1][j+1] = null;
				} else {
					localEnv[i+1][j+1] = this.espace[newX+i][newY+j];
				
				}

			}
		}
//		for(int i = 0; i < localEnv.length; i++){
//			for(int j = 0; j < localEnv.length; j++){
//				if(localEnv[i][j] == null)
//					System.out.print(" 0 ");
//				else
//					System.out.print(" 1 ");
//			}
//			System.out.println();
//		}
		return localEnv;
	}
	
	public List<Agent> getNeighboursList(int x, int y){
		List<Agent> localEnv = new ArrayList<Agent>();
		int newX = x;
		int newY = y;

		
		for(int i = -1; i < 3; i++){
			for(int j = -1; j < 3; j++){
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
		if(x < this.espace.length && x >= 0 && y < this.espace.length && y >=0){
			return this.espace[x][y] == null;
		}
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
	
	public void addAgent(Agent a) throws Exception{
		if(this.espace[a.getPosX()][a.getPosY()] != null)
			throw new Exception("Error : cannot place new agent on top of another one !");
		this.espace[a.getPosX()][a.getPosY()] = a;
		this.agents.add(a);
	}
	
	public List<Agent> getAgents(){
		return this.agents;
	}
	
	public void setAgent(int x, int y, int dirX, int dirY){
		this.espace[x][y] = new Particle(x, y, dirX, dirY, this);
		this.agents.add(espace[x][y]);
	}

	public boolean isToric() {
		return this.toric;
	}
	
	public int getEnvSize(){
		return this.espace.length;
	}
	
	public Agent getCell(int x, int y){
		return this.espace[x][y];
	}
	
	public int convertToToric(int val){
		return (val+this.espace.length)%this.espace.length;
	}

	public boolean moveAgent(Agent a, int newX, int newY) {
		if(this.espace[newX][newY] == null){
			this.espace[a.getPosX()][a.getPosY()] = null;
			this.espace[newX][newY] = a;
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
	 
	
}
