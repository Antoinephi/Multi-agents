import java.util.Arrays;


public class Agent {
	
	private int posX;
	private int posY;
	private int dirX;
	private int dirY;
	private Environnement env;
	private int[] localEnv;
	
	public Agent(int posX, int posY, int dirX, int dirY, Environnement env){
		this.posX = posX;
		this.posY = posY;
//		this.direction = direction;
		this.dirX = (dirX+env.getAgents().length-1)%env.getAgents().length;
		this.dirY = (dirY+env.getAgents().length-1)%env.getAgents().length;
		this.env = env;
		this.localEnv = env.getLocalEnv(posX, posY);
	}
	
	public void decide() throws Exception{

		if(env.update(this, ((posX+dirX)+env.getAgents().length-1)%env.getAgents().length, ((posY+dirY)+env.getAgents().length-1)%env.getAgents().length)){
			this.posX+=dirX;
			this.posX%=10;	
			this.posY+=dirY;
			this.posY%=10;
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
	
	
	
}
