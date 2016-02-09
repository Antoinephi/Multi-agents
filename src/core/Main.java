package core;

public class Main {
	
	public static void main(String[] args) {
		switch (args[0]) {
		case "-wator":
			System.out.println("YESS");
			break;
		case "-particles":
			break;
		case "-hunter":
			break;
		default:
			printHelp();
			break;
		}
	}

	private static void printHelp() {
		System.out.println("----- Help ----- ");
		System.out.println("Run : java -jar multi-agents.jar");
		System.out.println("-mode : [-particles|-wator|-hunter]");
		System.out.println("\n----Global parameters : ");
		System.out.println("\t [view size] : size of the view frame");
		System.out.println("\t [cell size] : size of one agent (inpact environnemnt's size (view/cell = env)");
		System.out.println("\t [simulation speed] : turns' duration (in ms)");
		System.out.println("\t [nbTurns] : number maximum of turns for simulation");
		System.out.println("\t [toric] : true/false : set if the map is toric or not ");
		System.out.println("\t [infinite mode] : true/false : run the sim for ever (unless Hunter wins or all sharks/tunas die)");
		System.out.println("\t [fair mode] : true/false : shuffle (or not) the agents' list before each turn");
		System.out.println("\t [Show grid] : true/false : show a grid within the view");		
		System.out.println("\n---- Wator parameters : ");
		System.out.println("\t [logging] : true/false : save in a log file wator results");
		System.out.println("\t [Shark hunger] : number of turns before sharks die");
		System.out.println("\t [Shark breed] : number of turns before sharks breed");
		System.out.println("\n----Hunter parameters : ");
		System.out.println("\t [hunters' speed] : speed of hunters compared to target");
		

	}

}
