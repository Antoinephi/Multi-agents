import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View implements Observer 	{

	private JPanel panel;
	private JFrame frame;
	
	public View(int gridSize, String name) {
		this.frame = new JFrame(name);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(500,500));
		this.panel.setBackground(Color.white);
		
		this.frame.setContentPane(this.panel);
		this.frame.pack();
	}
	
	public void update(Observable arg0, Object arg1) {
		SMA sma = (SMA)arg0;
		Graphics g = this.panel.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 500);
//		frame.repaint();
		for(Agent a : sma.getAgents()) {
//			System.out.println("DRAX");
			draw(a, g);
//			erase(a, g);
		}
		
	}
	

	public void draw(Agent a, Graphics g){
			g.setColor(a.getColor());
			g.fillOval(a.getPosX()*5, a.getPosY()*5, 10, 10);
	}
	
	public void erase(Agent a, Graphics g){
		g.setColor(this.panel.getBackground());
		g.fillOval(a.getPosX()*10, a.getPosY()*10, 10, 10);
	}
	
	
}
