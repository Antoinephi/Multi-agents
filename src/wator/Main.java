package wator;

import particles.ParticlesView;
import core.SMA;
import core.View;

public class Main {
	
	private static final int VIEW_SIZE = 1000;
	private static final int CELL_SIZE = 8;
	private static final int NB_AGENTS = 500;
	private static final int SIM_SPEED = 30;
	private static final int NB_TURNS = 10000;
	private static final boolean TORIC = false;
	private static final int SHARK_HUNGER = 5;
	private static final int SHARK_BREED = 3;
	private static final int TUNA_BREED = 3;
	private static final int NB_SHARKS = 30;
	private static final int NB_TUNA = 100;
	

	public static void main(String[] args) throws Exception {
		View v = new ParticlesView(VIEW_SIZE,CELL_SIZE, "Wator simulation");

		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS);
		for(int i = 0; i < NB_AGENTS; i++){
			new Shark(sma.getEnv(), SHARK_HUNGER, SHARK_BREED);
		}
		sma.run(NB_TURNS);
		
	}
	
}
