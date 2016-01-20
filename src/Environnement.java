import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Environnement {
	
	private Agent[][] espace;
	private List<Agent> agents;
//	private Agent[][] agents;
	private static int MAX_AGENT = 10;
	private boolean toric = false;
	Random r = new Random();
	
	public Environnement(int sizeX, int sizeY, boolean toric){
		this.espace = new Agent[sizeX][sizeY];
		agents = new LinkedList<Agent>();
		init();
		this.toric = toric;
		
	}
	
	private void init(){
		int nbAgents = 0;
		while(nbAgents < MAX_AGENT){
			for(int i = 0; i < espace.length; i++ ){
				for(int j = 0; j < espace.length; j++){
					if(r.nextInt(1000) > 998 && MAX_AGENT > nbAgents && espace[i][j] == null){
						espace[i][j] = new Agent(i, j, r.nextInt(2)+1,r.nextInt(3)-1, this);
						this.agents.add(espace[i][j]);
//						System.out.println(espace[i][j]);
						nbAgents++;
					}
				}
			}
		}
	}
	
	public int[] getLocalEnv(int x, int y){
		int[] localEnv = new int[9];
		int cursor = 0;
		for(int i = x-1; i<x+1; i++){
			for(int j = y-1; j < y+1; j++){
				if(i == x && j == y) continue;
				if(i < 0)
					i += espace.length;
				if(j < 0)
					j += espace.length;
				localEnv[cursor] = (espace[i%espace.length][j%espace.length] == null ? 0 : 1);
			}
		}
		
		return localEnv;
	}

	public boolean isAvailable(int x, int y) {
//		System.out.println("x " + x +" y " +  y);
//		System.out.println("x " + (x+espace.length-1)%espace.length +" y " +  (y+espace.length-1)%espace.length);
//		if(!this.toric)
//			return this.espace[(x+espace.length)%espace.length][(y+espace.length)%espace.length] == null;
		
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
			System.out.println("x : " + x + " y : " +y);
			oldX = (oldX+espace.length)%espace.length;
			oldY = (oldY+espace.length)%espace.length;
		}
		if(isAvailable(newX, newY)){
//			System.out.println("x : " + x + " y : " +y);
			this.espace[newX][newY] = agent;
//			System.out.println("x : " + agent.getPosX() + " y : " +agent.getPosY());
			this.espace[oldX][oldY] = null;
			return true;
		} else {
			agent.setDirX(-1*agent.getDirX());
			agent.setDirY(-1*agent.getDirY());
			if(x < this.espace.length-1 && x >= 0 && y < this.espace.length-1 && y >=0){			
				if(this.espace[x][y] != null){
					this.espace[x][y].setDirX(-1*this.espace[x][y].getDirX());
					this.espace[x][y].setDirY(-1*this.espace[x][y].getDirY());
	//				this.espace[x][y].decide();
	//				(espace[x][y], (x+this.espace[x][y].getDirX()+this.espace.length)%this.espace.length, (y+this.espace[x][y].getDirY()+this.espace.length)%this.espace.length);
				}
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
	
	public List<Agent> getAgents(){
		return this.agents;
	}
	
	public void setAgent(int x, int y, int dirX, int dirY){
		this.espace[x][y] = new Agent(x, y, dirX, dirY, this);
		this.agents.add(espace[x][y]);
	}

	public boolean isToric() {
		return this.toric;
	}
	
	 
	
}
