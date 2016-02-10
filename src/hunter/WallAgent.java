package hunter;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class WallAgent extends Agent{

	public WallAgent(int posX, int posY, Environnement env) {
		super(posX, posY, env);
		this.c = new Color(138,138,138);
	}

	public WallAgent(Environnement env) {
		super(env);
		this.c = new Color(138,138,138);

	}

	public void decide() throws Exception {
		
	}

}
