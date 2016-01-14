import java.util.Random;


public class Environnement {
	
	private Agent[][] espace;
//	private List<Agent> agents;
//	private Agent[][] agents;
	private static int MAX_AGENT = 1;
	
	Random r = new Random();
	
	public Environnement(int sizeX, int sizeY){
		this.espace = new Agent[sizeX][sizeY];
//		agents = new ArrayList<Agent>();
//		agents = new Agent[sizeX][sizeY];
		init();
		
	}
	
	private void init(){
		int nbAgents = 0;
		while(nbAgents < MAX_AGENT){
			for(int i = 0; i < espace.length; i++ ){
				for(int j = 0; j < espace.length; j++){
					if(r.nextInt(5) > 3 && MAX_AGENT > nbAgents && espace[i][j] == null){
//						System.out.println("after");
//						System.out.println("loop : i :" + i + " j : " + j);
						espace[i][j] = new Agent(i, j, r.nextInt(2)-1,r.nextInt(3)-1, this);
						System.out.println(espace[i][j].getDirX() +" "+ espace[i][j].getDirY());
						nbAgents++;
					}
				}
			}
		}
		System.out.println("NUM AGENTS : " + nbAgents);
	}
	

	public void update() throws Exception{
		for(Agent[] agents : espace){
			for(Agent a : agents)
				a.decide(); 				
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
		return this.espace[(x+espace.length-1)%espace.length][(y+espace.length-1)%espace.length] == null;
	}

	
	public boolean update(Agent agent, int x, int y) throws Exception {
		if(isAvailable(x, y)){
			this.espace[(x+espace.length-1)%espace.length][(y+espace.length-1)%espace.length] = agent;
			this.espace[agent.getPosX()][agent.getPosY()] = null;
			return true;
		} else {
			agent.setDirX(-1*agent.getDirX());
			agent.setDirY(-1*agent.getDirY());
		}
		return false;
		
	}
		
	public void print(){
		for(int i = 0; i < espace.length; i++ ){
			System.out.println();
			for(int j = 0; j < espace.length; j++){
				if(espace[i][j] == null)
					System.out.print(" 0 ");
				else
					System.out.print(" 1 ");
			}
		}
		System.out.println();
	}

	public Agent[][] getAgents() {
		return espace;
	}
	
	 
	
}
