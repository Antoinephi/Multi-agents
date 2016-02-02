package wator;

import java.awt.Graphics;

import core.Agent;
import core.View;

public class WatorView extends View{

	public WatorView(int gridSize, int cellSize, String name, boolean showGrid) {
		super(gridSize, cellSize, name, showGrid);
	}
	
	public void draw(Agent a, int x, int y, Graphics g){
		g.setColor(a.getColor());
		g.fillRect(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
	}
	
}