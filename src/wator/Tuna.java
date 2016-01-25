package wator;

import java.awt.Color;

import core.Environnement;

public class Tuna extends Fish {

	public Tuna(int posX, int posY, int dirX, int dirY, Environnement env) {
		super(posX, posY, dirX, dirY, env);
		this.c = new Color(150, 0, 0);
	}
	
	public Tuna(Environnement env) {
		super(env);
		this.c = new Color(150, 0, 0);
	}
	
	@Override
	public void decide() throws Exception {
		
	}

	@Override
	public String getTypeOf() {
		return "tuna";
	}


}
