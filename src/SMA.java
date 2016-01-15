import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;



public class SMA {
	
	private Environnement env;
	private static List<Agent> agents;;
	
	public SMA(){
		env = new Environnement(10,10);
		env.print();
		agents = env.getAgents();
	}
	
	public void run(int nbTurns) throws Exception{
		for(int i = 0; i < nbTurns; i++){
			turn();
			Thread.sleep(1000);
			env.print();
		}
	}
	

	public void turn() throws Exception{
		System.out.println("Nombre d'agents : " + agents.size());
		for(Agent a : agents){
			if(a !=  null)
			a.decide();
		}
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
	

	public static void main(String[] args) throws Exception {
		SMA sma = new SMA();
		sma.run(1000);
//		
//		for(int x = 0; x <20; x++){
//			System.out.println("x : " + x + " %  : " + (x+10)%10);
//		
//	}
	}
	
}
