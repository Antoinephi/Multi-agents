package wator;

import java.awt.Color;

import core.Environnement;

public class Tuna extends Fish {

	public Tuna(int posX, int posY, int dirX, int dirY, Environnement env, int nbBreed) {
		super(posX, posY, dirX, dirY, env, nbBreed);
		this.c = new Color(150, 0, 0);
	}
	
	public Tuna(Environnement env, int nbBreed) {
		super(env, nbBreed);
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
