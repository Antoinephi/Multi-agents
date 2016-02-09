package particles;

import core.SMA;
import core.View;

public class Main {
	
	private static final int VIEW_SIZE = 1000;
	private static final int CELL_SIZE = 8;
	private static final int NB_AGENTS = 500;
	private static final int SIM_SPEED = 30;
	private static final int NB_TURNS = 10000;
	private static final boolean TORIC = true;
<<<<<<< HEAD
	private static final boolean INFINITE_MODE = false;
=======
	public static final boolean INFINITE_MODE = true;
>>>>>>> c77874ef1dfacc28cdbbf1afc83e8182d35cbf5e
	private static final boolean LOGGING = false;
	private static final boolean FAIR_MODE = true;
	private static final boolean SHOW_GRID = true;


	public static void main(String[] args) throws Exception {
		View v = new ParticlesView(VIEW_SIZE,CELL_SIZE, "Particles simulation", SHOW_GRID);

		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS, INFINITE_MODE, LOGGING, FAIR_MODE, 0);
		sma.setParticleMode();
		for(int i = 0; i < NB_AGENTS; i++){
			new Particle(sma.getEnv());
		}
		sma.run(NB_TURNS);
		
	}
	
}
