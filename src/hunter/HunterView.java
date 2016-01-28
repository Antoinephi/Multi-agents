package hunter;

import java.awt.Graphics;

import core.Agent;
import core.View;

public class HunterView extends View{

	public HunterView(int gridSize, int cellSize, String name) {
		super(gridSize, cellSize, name);
	}
	
	public void draw(Agent a, Graphics g){
		g.setColor(a.getColor());
		g.fillRect(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
	}
	
}