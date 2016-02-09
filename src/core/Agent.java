package core;
import java.awt.Color;
import java.util.Random;


public abstract class Agent {
	
	protected int posX;
	protected int posY;
	protected int dirX;
	protected int dirY;
	protected Environnement env;
	protected Color c;
	protected Random r = new Random();
	
	public Agent(int posX, int posY, Environnement env){
		this.posX = posX;
		this.posY = posY;
		this.env = env;
		try {
			this.env.addAgent(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Agent(Environnement env){
		this.env = env;
		
		int posX = r.nextInt(env.getEnvSize()-1);
		int posY = r.nextInt(env.getEnvSize()-1);
		while(env.getCell(posX, posY) != null){
			posX = r.nextInt(env.getEnvSize()-1);
			posY = r.nextInt(env.getEnvSize()-1);
		}
		this.posX = posX;
		this.posY = posY;
		this.dirX = r.nextInt(3)-1;
		this.dirY = r.nextInt(3)-1;
		try {
			this.env.addAgent(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void decide() throws Exception;
	
	
	@Override
	public String toString() {
		return "Agent [posX=" + posX + ", posY=" + posY + ", dirX=" + dirX
				+ ", dirY=" + dirY + ", env=" + env  + "]";
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public void setDirX(int x) {
		this.dirX = x;
	}

	public void setDirY(int y) {
		this.dirY = y;
	}
	
	public int getDirX(){
		return this.dirX;
	}
	
	public int getDirY(){
		return this.dirY;
	}
	
	public Color getColor(){
		return this.c;
	}
	
//	public boolean equals(Object o){
//		return this.posX == ((Agent)o).getPosX() && this.posY == ((Agent)o).getPosY();
//	}
}
