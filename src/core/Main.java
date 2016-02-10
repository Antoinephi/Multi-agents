package core;

import particles.Particle;
import hunter.Hunter;
import hunter.HunterView;
import hunter.Target;
import hunter.WallAgent;
import wator.Shark;
import wator.Tuna;

public class Main {
	
	private static int VIEW_SIZE = 500;
	private static int CELL_SIZE = 5;
	private static int SIM_SPEED = 50;
	private static int NB_AGENTS = 5;
	private static int NB_TURNS = 10000;
	private static boolean TORIC = true;
	private static boolean INFINITE_MODE = true;
	private static boolean LOGGING = false;
	private static int SHARK_HUNGER = 3;
	private static int SHARK_BREED = 9;
	private static int TUNA_BREED = 2;
	private static int NB_SHARKS = 500;
	private static int NB_TUNA = 1000;
	private static boolean FAIR_MODE = true;
	private static boolean SHOW_GRID = true;
	private static int SPEED_FACTOR = 1;

	
	public static void main(String[] args) throws Exception {
		if(args.length == 0){
			printHelp();
			return;
		}
		switch(args[0]) {
		case "-wator":
			wator(args);
			break;
		case "-particles":
			particles(args);
			break;
		case "-hunter":
			hunter(args);
			break;
		default:
			printHelp();
			break;
		}
	}
	
	private static void particles(String[] args) throws Exception{
		
		VIEW_SIZE = 1000;
		CELL_SIZE = 8;
		SIM_SPEED = 30;
		NB_AGENTS = 500;
		NB_TURNS = 10000;
		TORIC = false;
		INFINITE_MODE = true;
		LOGGING = false;
		SHOW_GRID = false;
		
		try {
			if(args.length > 1){
				VIEW_SIZE = Integer.parseInt(args[1]);
				CELL_SIZE = Integer.parseInt(args[2]);
				NB_AGENTS = Integer.parseInt(args[3]);
				SIM_SPEED = Integer.parseInt(args[4]);
				TORIC = args[5].equals("true") ? true : false;
				INFINITE_MODE = args[6].equals("true") ? true : false;
				SHOW_GRID = args[7].equals("true") ? true : false;
			}
		} catch(Exception e){
			printHelp();
			return;
		}

		
		
		View v = new View(VIEW_SIZE,CELL_SIZE, "Particles simulation", SHOW_GRID);

		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS, INFINITE_MODE, LOGGING, FAIR_MODE, 0);
		sma.setParticleMode();
		for(int i = 0; i < NB_AGENTS; i++){
			new Particle(sma.getEnv());
		}
		sma.run(NB_TURNS);
		
	}
	
	private static void hunter(String[] args) throws Exception{
		VIEW_SIZE = 500;
		CELL_SIZE = 10;
		SIM_SPEED = 60;
		NB_AGENTS = 5;
		NB_TURNS = 10000;
		TORIC = false;
		INFINITE_MODE = true;
		SHOW_GRID = false;
		SPEED_FACTOR = 2;

		try {
	
			if(args.length > 1){
				VIEW_SIZE = Integer.parseInt(args[1]);
				CELL_SIZE = Integer.parseInt(args[2]);
				NB_AGENTS = Integer.parseInt(args[4]);
				SIM_SPEED = Integer.parseInt(args[5]);
				SPEED_FACTOR = Integer.parseInt(args[6]);
				TORIC = args[7].equals("true") ? true : false;
				SHOW_GRID = args[8].equals("true") ? true : false;
			}
		} catch(Exception e){
			printHelp();
			return;

		}

		View v = new HunterView(VIEW_SIZE,CELL_SIZE, "Hunter simulation", SHOW_GRID);
		
		SMA sma = new SMA(NB_AGENTS,VIEW_SIZE, CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS, INFINITE_MODE, LOGGING, FAIR_MODE, SPEED_FACTOR);
		
		for(int i = 0; i < NB_AGENTS; i++){
			new Hunter(sma.getEnv());
		}
		
		for(int i = 0; i <((VIEW_SIZE/CELL_SIZE)*2); i++){
			new WallAgent(sma.getEnv());
		}
		
		Target t  = new Target(sma.getEnv());
		v.addKeyListener(t);
		
		sma.run(NB_TURNS);
	}
	
	private static void wator(String[] args) throws Exception{
		VIEW_SIZE = 500;
		CELL_SIZE = 5;
		SIM_SPEED = 50;
		NB_TURNS = 10000;
		TORIC = true;
		INFINITE_MODE = true;
		LOGGING = false;
		SHOW_GRID = true;
		SHARK_HUNGER = 3;
		SHARK_BREED = 9;
		TUNA_BREED = 2;
		NB_SHARKS = 500;
		NB_TUNA = 1000;

		try {
		
			if(args.length > 1){
				VIEW_SIZE = Integer.parseInt(args[1]);
				CELL_SIZE = Integer.parseInt(args[2]);
				SIM_SPEED = Integer.parseInt(args[3]);
				TORIC = args[4].equals("true") ? true : false;
				INFINITE_MODE = args[5].equals("true") ? true : false;
				LOGGING =  args[6].equals("true") ? true : false;
				NB_SHARKS = Integer.parseInt(args[7]);
				SHARK_HUNGER = Integer.parseInt(args[8]);
				SHARK_BREED = Integer.parseInt(args[9]);
				NB_TUNA = Integer.parseInt(args[11]);
				TUNA_BREED = Integer.parseInt(args[11]);
				SHOW_GRID = args[12].equals("true") ? true : false;
			}
		} catch(Exception e){
			printHelp();
			return;

		}

		View v = new View(VIEW_SIZE,CELL_SIZE, "Wator simulation", SHOW_GRID);

		SMA sma = new SMA(NB_TUNA+NB_SHARKS,VIEW_SIZE, 
				CELL_SIZE, SIM_SPEED, TORIC, v, NB_TURNS, INFINITE_MODE, LOGGING, FAIR_MODE, 0);
		for(int i = 0; i < NB_TUNA; i++){
			new Tuna(sma.getEnv(), TUNA_BREED);
		}
		for(int i = 0; i < NB_SHARKS; i++){
			new Shark(sma.getEnv(), SHARK_BREED, SHARK_HUNGER);
		}

		sma.run(NB_TURNS);
	}

	private static void printHelp() {
		System.out.println("----- Help ----- ");
		System.out.println("Run : java -jar multi-agents.jar");
		System.out.println("-mode : [-particles|-wator|-hunter]");
		System.out.println("\n----Global parameters : ");
		System.out.println("\t [view size] : size of the view frame > default : 500");
		System.out.println("\t [cell size] : size of one agent (inpact environnemnt's size (view/cell = env)");
		System.out.println("\t [nb Agents] : number of agents (only for particles and hunter)");
		System.out.println("\t [simulation speed] : turns' duration (in ms) > default : 60 ms");
		System.out.println("\t [nbTurns] : number maximum of turns for simulation");
		System.out.println("\t [toric] : true/false : set if the map is toric or not > default : false");
		System.out.println("\t [infinite mode] : true/false : run the sim for ever (unless Hunter wins or all sharks/tunas die)");
		System.out.println("\t [fair mode] : true/false : shuffle (or not) the agents' list before each turn");
		System.out.println("\t [Show grid] : true/false : show a grid within the view");		
		System.out.println("\n---- Wator parameters : ");
		System.out.println("\t [logging] : true/false : save in a log file wator results");
		System.out.println("\t [nb sharks] : number of sharks");
		System.out.println("\t [Shark hunger] : number of turns before sharks die");
		System.out.println("\t [Shark breed] : number of turns before sharks breed");
		System.out.println("\t [nb tunas] : number of tunas");
		System.out.println("\t [tuna breed] : number of turns before tunas breed");
		System.out.println("\n----Hunter parameters : ");
		System.out.println("\t [hunters' speed] : speed factor of hunters compared to target");
		

	}

}
