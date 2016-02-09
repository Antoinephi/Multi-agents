package hunter;

import core.SMA;
import core.View;

public class Main {
	
	private static final int VIEW_SIZE = 500;
	private static final int CELL_SIZE = 10;
	private static final int NB_AGENTS = 5;
	private static final int SIM_SPEED = 10;
	private static final int NB_TURNS = 1000;
	private static final boolean TORIC = false;
	private static final boolean INFINITE_MODE = true;
	private static final boolean LOGGING = false;
	private static final boolean FAIR_MODE = false;
	private static final boolean SHOW_GRID = true;
	private static final int SPEED_FACTOR = 2;

	public static void main(String[] args) throws Exception {
		View v = new HunterView(VIEW_SIZE,CELL_SIZE, "Hunter simulation", SHOW_GRID);
		
		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS, INFINITE_MODE, LOGGING, FAIR_MODE, SPEED_FACTOR);
		
		for(int i = 0; i < NB_AGENTS; i++){
			new Hunter(sma.getEnv());
		}
		
		for(int i = 0; i <((VIEW_SIZE/CELL_SIZE)*2); i++){
			new WallAgent(sma.getEnv());
		}

//		Repulsor r = new Repulsor(sma.getEnv());
		
		Target t  = new Target(sma.getEnv());
		v.addKeyListener(t);
//		r.setTarget(t);
	

		

		
		sma.run(NB_TURNS);
		
	}
	
}
