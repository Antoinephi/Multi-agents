package wator;

import java.awt.Color;

import core.Environnement;

public class Shark extends Fish {
	
	private static int INIT_HUNGER;

	private int hunger;
	
	
	public Shark(int posX, int posY, Environnement env, int nbBreed, int hunger) {
		super(posX, posY, env, nbBreed);
		this.c = new Color(24, 27, 255);
		INIT_HUNGER = hunger;
		this.hunger = hunger;
	}
	
	public Shark(Environnement env, int nbBreed, int hunger) {
		super(env, nbBreed);
		this.c = new Color(24, 27, 255);
		INIT_HUNGER = hunger;
		this.hunger = hunger;
	}

	public void decide() throws Exception {
		this.updateParameters();
		if(!this.isAlive){
			System.out.println("DEAD shark");
			this.env.addDeadAgent(this);
			if(this.env.getCell(posX, posY) == this)
				this.env.getEspace()[posX][posY] = null;
			return;
		}
		if(hunger <= 0) {
			this.env.addDeadAgent(this);
			this.kill();
			this.env.getEspace()[this.posX][this.posY] = null;
			return;
		} 
		this.getCurrentNeighbourhood();
		
		if(nbBreed <= 0 && nearbyEmptyCells.size() > 0 && this.env.getAgents().size() < this.env.getEnvSize()*this.env.getEnvSize()){
			if(this.env.isAvailable(this.nearbyEmptyCells.get(0)[0], this.nearbyEmptyCells.get(0)[1]))
				this.reproduce(new Shark(this.nearbyEmptyCells.get(0)[0], this.nearbyEmptyCells.get(0)[1], this.env,INIT_BREED, INIT_HUNGER));
			if(this.nearbyEmptyCells.size() > 1)
				this.move(this.nearbyEmptyCells.get(1)[0], this.nearbyEmptyCells.get(1)[1]);
		} else if(closeTunas.size() > 0){
			this.eatFish(closeTunas.get(0));
			this.move(this.closeTunas.get(0).getPosX(), this.closeTunas.get(0).getPosY());

			this.closeTunas.remove(0);
		} else if(nearbyEmptyCells.size() > 0){
			this.move(nearbyEmptyCells.get(0)[0], nearbyEmptyCells.get(0)[1]);
		}
		
	}
	
	protected void updateParameters(){
		super.updateParameters();
		this.hunger--;
	}

	private void eatFish(Tuna t){
		if(t == null) return;
		this.env.addDeadAgent(t);
		t.kill();
		this.env.getEspace()[t.getPosX()][t.getPosY()] = null;

		this.hunger = INIT_HUNGER;
		int[] coo = {t.getPosX(),t.getPosY()};
		this.nearbyEmptyCells.add(coo);
	}
	
	public String getTypeOf() {
		return "shark";
	}


}
