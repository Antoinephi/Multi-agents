package core;
import java.awt.Color;
import java.util.Arrays;
import java.util.Random;


public abstract class Agent {
	
	protected int posX;
	protected int posY;
	protected int dirX;
	protected int dirY;
	protected Environnement env;
	protected Color c;
	protected Random r = new Random();
	
	public Agent(int posX, int posY, int dirX, int dirY, Environnement env){
		this.posX = posX;
		this.posY = posY;
		this.dirX = dirX;
		this.dirY = dirY;
		this.env = env;
		this.c = new Color(r.nextInt(230), r.nextInt(230), r.nextInt(230));
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
		this.c = new Color(r.nextInt(230), r.nextInt(230), r.nextInt(230));
	}
	
	public abstract void decide() throws Exception;
	
	public boolean isAvailable(int x, int y){
		if(x < 0 || y < 0)
			return false;
		Agent[][] localEnv = this.env.getLocalEnv(this.posX, this.posY);
		int localX = (this.posX-x+localEnv.length)%(localEnv.length);
		int localY = (this.posY-y+localEnv.length)%(localEnv.length);
		System.out.println("posX : " + posX  + " x : " + x + " localX : " + localX );
		System.out.println("posY : " + posY + " y : " + y + " localY : " + localY);
		return localEnv[localX][localY] == null;		
	}
	
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
	
	
}
