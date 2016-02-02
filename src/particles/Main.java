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
	public static final boolean INFINITE_MODE = true;
	private static final boolean LOGGING = false;
	

	public static void main(String[] args) throws Exception {
		View v = new ParticlesView(VIEW_SIZE,CELL_SIZE, "Particles simulation");

		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS, INFINITE_MODE, LOGGING);
		for(int i = 0; i < NB_AGENTS; i++){
			new Particle(sma.getEnv());
		}
		sma.run(NB_TURNS);
		
	}
	
}
