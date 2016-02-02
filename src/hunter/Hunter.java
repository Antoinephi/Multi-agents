package hunter;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Hunter extends Agent {

	private Target target;
	private int[][] map;
	private int distance = Integer.MAX_VALUE;

	public Hunter(int posX, int posY, Environnement env) {
		super(posX, posY, env);
		this.c = new Color(0, 128, 0);
		this.map = new int[this.env.getEnvSize()][this.env.getEnvSize()];
	}

	public Hunter(Environnement env) {
		super(env);
		this.c = new Color(0, 128, 0);
		this.map = new int[this.env.getEnvSize()][this.env.getEnvSize()];
	}

	@Override
	public void decide() throws Exception {
		map = new int[this.env.getEnvSize()][this.env.getEnvSize()];
		distance = Integer.MAX_VALUE;
		int[] bestMove;
		System.out.println("Current pos : " + posX + ":"+ posY);
		
		target = this.env.getChasedAgent();
		if(target != null){
			this.map = target.getMap();
//			printMap();
			bestMove = this.bestLocalPath();
			System.out.println("BestLocalPIck : " + bestMove[0] + ":" + bestMove[1]);
			System.out.println("Target : " +  target.getPosX() + ":" + target.getPosY());
			if(this.env.getCell(bestMove[0], bestMove[1]) != null && this.env.getCell(bestMove[0], bestMove[1]).equals(target))
				this.target.kill();
			if (this.env.isAvailable(bestMove[0], bestMove[1])) {
				System.out.println("best move used");
				this.env.moveAgent(this, bestMove[0], bestMove[1]);
				this.posX = bestMove[0];
				this.posY = bestMove[1];
			} 
	
		}
	}

	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(" " + map[j][i]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public int[] bestLocalPath(){
		int[] coo = new int[2];
		for (int i = posX - 1; i <= posX + 1; i++) {
			for (int j = posY - 1; j <= posY + 1; j++) {
				if (i >= 0 && j >= 0 && i < this.env.getEnvSize()
						&& j < this.env.getEnvSize()){
					if(this.map[i][j] != -1 && this.map[i][j] < distance){
						coo[0] = i;
						coo[1] = j;
						distance = this.map[i][j];
						System.out.println("i : " + i + " j : "+ j + " map : " + map[i][j]);
					}
				}
					
			}
		}
		
		return coo;
	}

}
