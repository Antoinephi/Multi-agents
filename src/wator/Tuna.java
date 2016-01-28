package wator;

import java.awt.Color;

import core.Environnement;

public class Tuna extends Fish {

	public Tuna(int posX, int posY, Environnement env, int nbBreed) {
		super(posX, posY, env, nbBreed);
		this.c = new Color(208, 0, 0);
	}
	
	public Tuna(Environnement env, int nbBreed) {
		super(env, nbBreed);
		this.c = new Color(208, 0, 0);
	}
	
	@Override
	public void decide() throws Exception {
		if(!this.isAlive){
//			System.out.println("DEAD : tuna");
			return;
		}
		this.updateParameters();

		this.getCurrentNeighbourhood();
		if(nbBreed <= 0 && nearbyEmptyCells.size() > 0){
			if(this.env.isAvailable(this.nearbyEmptyCells.get(0)[0], this.nearbyEmptyCells.get(0)[1]))
				this.reproduce(new Tuna(this.nearbyEmptyCells.get(0)[0], this.nearbyEmptyCells.get(0)[1], this.env, INIT_BREED));
			if(this.nearbyEmptyCells.size() > 1)
				this.move(this.nearbyEmptyCells.get(1)[0], this.nearbyEmptyCells.get(1)[1]);
		} else if(this.nearbyEmptyCells.size() > 0){
			this.move(this.nearbyEmptyCells.get(0)[0], this.nearbyEmptyCells.get(0)[1]);
		}
	}

	@Override
	public String getTypeOf() {
		return "tuna";
	}


}
