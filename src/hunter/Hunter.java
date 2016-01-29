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
//		System.out.println("Current pos : " + posX + ":"+ posY);
		
		target = this.env.getChasedAgent();
		if(target != null){
			this.map[target.getPosX()][target.getPosY()] = 0;
			distance(target.getPosX(), target.getPosY());
//			printMap();
			bestMove = this.bestLocalPath();
//			System.out.println("BestLocalPIck : " + bestMove[0] + ":" + bestMove[1]);
//			System.out.println("Target : " +  target.getPosX() + ":" + target.getPosY());
			if(this.env.getCell(bestMove[0], bestMove[1]) != null && this.env.getCell(bestMove[0], bestMove[1]).equals(target))
				this.target.kill();
//			System.out.println("killed");
			if (this.env.isAvailable(bestMove[0], bestMove[1])) {
//				System.out.println("best move used");
				this.env.moveAgent(this, bestMove[0], bestMove[1]);
				this.posX = bestMove[0];
				this.posY = bestMove[1];
			} 
	
		}
	}


	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public void distance(int x, int y) {
//		System.out.println("call");
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i < 0 || j < 0 || i >= this.env.getEnvSize()
						|| j >= this.env.getEnvSize()){
//					System.out.println("returned" + i+":"+j);
				} else {
					if (this.env.getCell(i, j) == null) {
						if (this.map[i][j] == 0 || this.map[i][j] > this.map[x][y]+1) {
//							System.out.println("updated map");
							this.map[i][j] = map[x][y]+1;
							this.distance(i, j);
						}
					} else if (this.env.getCell(i, j).equals(this) || this.env.getCell(i, j) instanceof WallAgent) {
						this.map[i][j] = -1;
					}
				}
			}
		}
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
					}
				}
					
			}
		}
		
		return coo;
	}

}
