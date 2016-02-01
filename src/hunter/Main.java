package hunter;

import core.SMA;
import core.View;

public class Main {
	
	private static final int VIEW_SIZE = 200;
	private static final int CELL_SIZE = 20;
	private static final int NB_AGENTS = 1;
	private static final int SIM_SPEED = 200;
	private static final int NB_TURNS = 1000;
	private static final boolean TORIC = false;
	private static final boolean INFINITE_MODE = true;
	private static final boolean LOGGING = false;
	

	public static void main(String[] args) throws Exception {
		View v = new HunterView(VIEW_SIZE,CELL_SIZE, "Hunter simulation", true);
		
		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS, INFINITE_MODE, LOGGING);
//		for(int i = 0; i <((VIEW_SIZE/CELL_SIZE)); i++){
//			new WallAgent(sma.getEnv());
//		}

		Target t  = new Target(sma.getEnv());
		v.addKeyListener(t);
		for(int i = 0; i < NB_AGENTS; i++){
			new Hunter(sma.getEnv());
		}
		
		sma.run(NB_TURNS);
		
	}
	
}
