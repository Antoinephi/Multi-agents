package particules;

import java.awt.Graphics;
import java.util.Observable;

import core.Agent;
import core.View;

public class ParticulesView extends View{

	public ParticulesView(int gridSize, int cellSize, String name) {
		super(gridSize, cellSize, name);
	}
	
	public void draw(Agent a, Graphics g){
		g.setColor(a.getColor());
		g.fillOval(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
	}
	
}