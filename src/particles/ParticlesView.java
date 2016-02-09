package particles;

import java.awt.Graphics;

import core.Agent;
import core.View;

public class ParticlesView extends View{

	public ParticlesView(int gridSize, int cellSize, String name,boolean showGrid) {
		super(gridSize, cellSize, name,showGrid);
	}
	
	public void draw(Agent a, int x, int y, Graphics g){
		g.setColor(a.getColor());
		g.fillOval(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
	}
	
}
