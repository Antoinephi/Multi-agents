package core;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;


public class Cell extends JPanel{
	
	private Graphics g;
	private int x;
	private int y;
	private int r;
	
	

	public Cell(Graphics g, int x, int y) {
		super();
		this.g = g;
		this.x = x;
		this.y = y;
		this.r = 10;
		this.setBackground(Color.black);
	}
	
	public Cell(){
		super();
		this.r = 10;
		this.setBackground(Color.BLUE);
	}
	
	public void paintComponent(Graphics g){
		Random r = new Random();
		for(int i = 0; i < 10; i++){
			g.setColor(new Color(r.nextInt(256)));
			g.fillOval(i*10,i*10, 10, 10);
			this.setVisible(true);
		}

	}
	
	public void setPosX(int x){
		this.x = x;
	}
	
	public void setPoxY(int y){
		this.y = y;
	}
	
}
