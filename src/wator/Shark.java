package wator;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Shark extends Fish {

	public Shark(int posX, int posY, int dirX, int dirY, Environnement env) {
		super(posX, posY, dirX, dirY, env);
		this.c = new Color(80, 80, 240);
	}
	
	public Shark(Environnement env) {
		super(env);
		this.c = new Color(80, 80, 240);
	}

	public void decide() throws Exception {
		
	}

	public String getTypeOf() {
		return "shark";
	}


}
