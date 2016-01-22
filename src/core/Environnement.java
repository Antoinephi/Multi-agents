package core;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import particules.Particule;


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
				if(this.toric){
					newX = (x+i+this.espace.length)%this.espace.length;
					newY = (j+y+this.espace.length)%this.espace.length;
					localEnv[i+1][j+1] = this.espace[newX][newY]; 
				} else if(!this.toric && (x+i > this.espace.length || x+j > this.espace.length || x+i < 0 || y+j < 0)) {
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

	public boolean isAvailable(int x, int y) {
		if(x < this.espace.length && x >= 0 && y < this.espace.length && y >=0)
			return this.espace[x][y] == null;
		return false;
	}

	
	public boolean update(Agent agent, int x, int y) throws Exception {
		int newX = x;
		int newY = y;
		int oldX = agent.getPosX();
		int oldY = agent.getPosY();
		if(this.toric){
			newX = (newX+espace.length)%espace.length;
			newY = (newY+espace.length)%espace.length;
			oldX = (oldX+espace.length)%espace.length;
			oldY = (oldY+espace.length)%espace.length;
		}
		if(isAvailable(newX, newY)){
			this.espace[newX][newY] = agent;
			this.espace[oldX][oldY] = null;
			return true;
		} else {
			if(x < this.espace.length-1 && x >= 0 && y < this.espace.length-1 && y >=0){
				agent.setDirX(-1*agent.getDirX());
				agent.setDirY(-1*agent.getDirY());
				if(this.espace[x][y] != null){
					this.espace[x][y].setDirX(this.espace[x][y].getDirX() == 0 ? -1*agent.getDirX() : -1*this.espace[x][y].getDirX());
					this.espace[x][y].setDirY(this.espace[x][y].getDirY() == 0 ? -1*agent.getDirY() : -1*this.espace[x][y].getDirY());
				}
			} else {
				if(x >= this.espace.length-1 || x <= 1)
					agent.setDirX(-1*agent.getDirX());
				if(y >= this.espace.length-1 || y <= 1)
					agent.setDirY(-1*agent.getDirY());
			}
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
		this.espace[x][y] = new Particule(x, y, dirX, dirY, this);
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
	
	 
	
}
