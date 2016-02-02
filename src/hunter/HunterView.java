package hunter;

import java.awt.Color;
import java.awt.Graphics;

import core.Agent;
import core.View;

public class HunterView extends View{
	
	private boolean displayNum = false;

	public HunterView(int gridSize, int cellSize, String name, boolean displayNum, boolean showGrid) {
		super(gridSize, cellSize, name, showGrid);
		this.displayNum = displayNum;
	}
	
	public void draw(Agent a,int x, int y, Graphics g){
		if(displayNum && a == null){
//			g.setColor(new Color(Target.map[x][y]%255, 255, 255));
//			g.setColor(Color.BLACK);
//			g.drawString(Target.map[x][y]+"", x*cellSize, y*cellSize);
//			g.fillRect(x*this.cellSize, y*this.cellSize, this.cellSize, this.cellSize);
		} else if(a != null){
			g.setColor(a.getColor());
			g.fillRect(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
		}
	}
	

	
}