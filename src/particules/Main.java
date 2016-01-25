package particules;

import core.SMA;
import core.View;

public class Main {
	
	private static final int VIEW_SIZE = 1000;
	private static final int CELL_SIZE = 8;
	private static final int NB_AGENTS = 500;
	private static final int SIM_SPEED = 30;
	private static final int NB_TURNS = 1000;
	private static final boolean TORIC = false;
	

	public static void main(String[] args) throws Exception {
		View v = new ParticulesView(VIEW_SIZE,CELL_SIZE, "Particules simulation");

		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS);
		for(int i = 0; i < NB_AGENTS; i++){
			new Particule(sma.getEnv());
		}
//		new Particule(4, 5, 1,1,sma.getEnv());
		sma.run(NB_TURNS);
//		sma.getEnv().getLocalEnv(5, 5);
		
		
	}
	
}
