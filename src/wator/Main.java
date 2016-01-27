package wator;

import core.SMA;
import core.View;

public class Main {
	
	private static final int VIEW_SIZE = 500;
	private static final int CELL_SIZE = 5;
	private static final int SIM_SPEED = 5;
	private static final int NB_TURNS = 1000;
	private static final boolean TORIC = true;
	private static final int SHARK_HUNGER = 3;
	private static final int SHARK_BREED = 9;
	private static final int TUNA_BREED = 2;
	private static final int NB_SHARKS = 500;
	private static final int NB_TUNA = 1000;

	public static void main(String[] args) throws Exception {
		View v = new WatorView(VIEW_SIZE,CELL_SIZE, "Wator simulation");

		SMA sma = new SMA(NB_TUNA+NB_SHARKS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS);
		for(int i = 0; i < NB_TUNA; i++){
			new Tuna(sma.getEnv(), TUNA_BREED);
		}
		for(int i = 0; i < NB_SHARKS; i++){
			new Shark(sma.getEnv(), SHARK_BREED, SHARK_HUNGER);
		}

		sma.run(NB_TURNS);
		
	}
	
}
