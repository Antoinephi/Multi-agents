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
	
	public View(int gridSize) throws InterruptedException{
		frame = new JFrame("Billes");
		frame.setMinimumSize(new Dimension(200,200));
		frame.setVisible(true);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 200));
		panel.setBackground(Color.WHITE);

		
		frame.setContentPane(panel);
		this.draw(panel.getGraphics());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	public void draw(Graphics g){
		Random r = new Random();
		for(int i = 0; i < 10; i++){
			g.setColor(Color.BLUE);
			g.fillOval(i*10,i*10, 10, 10);
//			frame.setVisible(true);
		}
		panel.paint(g);
	}
	
	public void addAgent(Agent a){
		
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
