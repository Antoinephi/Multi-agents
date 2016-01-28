package hunter;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Hunter extends Agent {

	private Target target;
	private int[][] map;
	private int distance;

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
		// System.out.println("x : " + posX + " posY : " + posY);
		if (this.env.isAvailable(posX + 1, posY)) {
			this.env.moveAgent(this, this.posX + 1, this.posY);
			this.posX = this.env.convertInd(posX + 1);
			this.posY = this.env.convertInd(posY);
		}
		target = this.env.getChasedAgent();
		if(target != null){
			this.map[target.getPosX()][target.getPosY()] = 1;
			getLocalEnvironnement(target.getPosX(), target.getPosY());
			
		}
		printMap();
	}

	public void distance() {
		if (target == null)
			return;

	}

	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	public void getLocalEnvironnement(int x, int y) {
//		System.out.println("call");
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i < 0 || j < 0 || i >= this.env.getEnvSize()
						|| j >= this.env.getEnvSize())
					return;
				if (this.env.getCell(i, j) == null) {
					if (this.map[i][j] == 0) {
						this.map[i][j] = map[x][y]+1;
						this.getLocalEnvironnement(i, j);
					}
				} else if (this.env.getCell(i, j).equals(this)) {
					this.map[i][j] = -1;
				}

			}
		}
	}

}
