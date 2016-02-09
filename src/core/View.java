package core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View implements Observer 	{

	protected JPanel panel;
	protected JFrame frame;
	protected int cellSize;
	protected boolean showGrid;
	protected int gridSize;
	
	public View(int gridSize,int cellSize, String name, boolean showGrid) {

		this.frame = new JFrame(name);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.showGrid = showGrid;
		this.cellSize = cellSize;
		this.gridSize = gridSize;
		
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(gridSize,gridSize));
		this.panel.setBackground(Color.white);
		this.frame.setLocationRelativeTo(null);
		this.frame.setContentPane(this.panel);
		this.frame.pack();

	}
	
	public void update(Observable arg0, Object arg1) {
		SMA sma = (SMA)arg0;
		Graphics g = this.panel.getGraphics();
		panel.paint(g);
		if(showGrid)
			paintGrid(g);

//		for(int i = 0; i <sma.getEnv().getEnvSize(); i++){
//			for(int j = 0; j <sma.getEnv().getEnvSize(); j++){
			for(Agent a : sma.getAgents()) {
				draw(a,0,0, g);	
			}
//		}
		this.frame.pack();
        panel.requestFocusInWindow();

	}
	
	public void draw(Agent a, int x, int y, Graphics g){
		g.setColor(a.getColor());
		g.fillRect(a.getPosX()*this.cellSize, a.getPosY()*this.cellSize, this.cellSize, this.cellSize);
	}
	
	public void addKeyListener(KeyListener k){
		this.panel.addKeyListener(k);
	}
	
	public void paintGrid(Graphics g){
		g.setColor(Color.black);
		for(int i = 0; i < this.panel.getSize().getHeight(); i+=cellSize){
				g.drawLine(0, i, gridSize, i);
				g.drawLine(i, 0, i, gridSize);
		}
		this.frame.pack();
	}
		
}
