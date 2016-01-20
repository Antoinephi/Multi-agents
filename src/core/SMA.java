package core;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;



public class SMA extends Observable {
	
	private Environnement env;
	private static List<Agent> agents;
	private int speed;
	
	public SMA(int nbAgents, int viewSize, int cellSize, int speed, boolean toric) throws Exception{
		int envSize = viewSize/cellSize;
		env = new Environnement(envSize,envSize, nbAgents, toric);
		this.speed = speed;
		View v = new View(viewSize,cellSize, "Billes");
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
		
		for(Agent a : agents){
			a.decide();
		}

		this.setChanged();
		this.notifyObservers();		
		Collections.shuffle(agents);
	}
	
	 static void shuffleArray(int[] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
	public Environnement getEnv(){
		return this.env;
	}
	
	public List<Agent> getAgents(){
		return this.agents;
	}

	public static void main(String[] args) throws Exception {
		SMA sma = new SMA(500,1000, 8, 30, false);
		sma.run(1000);
//		
//		for(int x = 0; x <20; x++){
//			System.out.println("x : " + x + " %  : " + (x+10)%10);
//		
//	}
	}
	
}