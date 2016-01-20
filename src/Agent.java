import java.awt.Color;
import java.util.Arrays;
import java.util.Random;


public class Agent {
	
	private int posX;
	private int posY;
	private int dirX;
	private int dirY;
	private Environnement env;
	private int[] localEnv;
	private Color c;
	private Random r = new Random();
	public Agent(int posX, int posY, int dirX, int dirY, Environnement env){
		this.posX = posX;
		this.posY = posY;
		this.dirX = dirX;
		this.dirY = dirY;
		this.env = env;
		this.c = new Color(r.nextInt(200), r.nextInt(200), r.nextInt(200));
		this.localEnv = env.getLocalEnv(posX, posY);
	}
	
	public void decide() throws Exception{
//		System.out.println("posX : " +posX + " + dirX : " + dirX);

		if(env.isToric()){
			if(env.update(this, ((posX+dirX)+env.getEspace().length)%env.getEspace().length, ((posY+dirY)+env.getEspace().length)%env.getEspace().length)){
				this.posX = ((posX+dirX)+env.getEspace().length)%env.getEspace().length;	
				this.posY = ((posY+dirY)+env.getEspace().length)%env.getEspace().length;
			}
		} else {
			if(env.update(this, posX+dirX, posY+dirY)){
				this.posX +=dirX;
				this.posY +=dirY;
			}
		}
	}

	

	@Override
	public String toString() {
		return "Agent [posX=" + posX + ", posY=" + posY + ", dirX=" + dirX
				+ ", dirY=" + dirY + ", env=" + env + ", localEnv="
				+ Arrays.toString(localEnv) + "]";
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
