import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Cell {
	
	private Graphics g;
	private int x;
	private int y;
	private int r;
	
	

	public Cell(Graphics g, int x, int y, int r) {
		super();
		this.g = g;
		this.x = x;
		this.y = y;
		this.r = r;
		this.setBackground(Color.black);
	}



	public void drawCenteredCircle(Graphics g, int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  g.setColor(Color.BLUE);
		  g.fillOval(x,y,r,r);
		}
}
