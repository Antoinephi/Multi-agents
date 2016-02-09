package hunter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import core.Agent;
import core.SMA;
import core.View;

public class HunterView extends View{
	
	private boolean displayNum = false;

	public HunterView(int gridSize, int cellSize, String name, boolean displayNum, boolean showGrid) {
		super(gridSize, cellSize, name, showGrid);
		this.displayNum = displayNum;
	}
	
	public void update(Observable arg0, Object arg1) {
		SMA sma = (SMA)arg0;
		Graphics g = this.panel.getGraphics();
		panel.paint(g);
		if(showGrid)
			paintGrid(g);

		for(int i = 0; i <sma.getEnv().getEnvSize(); i++){
			for(int j = 0; j <sma.getEnv().getEnvSize(); j++){
				draw(sma.getEnv().getCell(i, j),i,j, g);	
			}
		}
		this.frame.pack();
        panel.requestFocusInWindow();

	}
	
	public void draw(Agent a,int x, int y, Graphics g){
		if(displayNum && a == null){
			g.setColor(new Color((Target.map[x][y]*5) > 255 ? 255 :(Target.map[x][y]*4) ));
//			g.setColor(Color.BLACK);
//			g.drawString(Target.map[x][y]+"", x*cellSize, y*cellSize);
			g.fillRect(x*this.cellSize, y*this.cellSize, this.cellSize, this.cellSize);
		} else if(a != null){
			g.setColor(a.getColor());
			g.fillRect(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
		}
	}
	

	
}