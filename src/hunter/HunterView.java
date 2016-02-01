package hunter;

import java.awt.Color;
import java.awt.Graphics;

import core.Agent;
import core.View;

public class HunterView extends View{
	
	private boolean displayNum = false;

	public HunterView(int gridSize, int cellSize, String name, boolean displayNum) {
		super(gridSize, cellSize, name);
		this.displayNum = displayNum;
	}
	
	public void draw(Agent a, Graphics g){
		if(displayNum){
			g.setColor(Color.BLACK);
//			g.drawString(Target.map[0][0]+"", (cellSize/2)-1, (cellSize/2)-1);
		}
		g.setColor(a.getColor());
		g.fillRect(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
	}
	

	
}