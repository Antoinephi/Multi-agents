import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View extends JFrame implements Observer 	{

	private List<JPanel> panels = new ArrayList<JPanel>();
	
	
	
	public View(int gridSize){
		this.setMinimumSize(new Dimension(1000,1000));
		this.setVisible(true);
//		this.setLayout(new GridLayout(10, 10));
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(500, 500));
		panel.setSize(100,100);
		panel.setBackground(Color.black);
		this.setContentPane(panel);
		pack();
		Graphics g = this.getGraphics();
	
		g.setColor(Color.BLUE);
		g.fillOval(50, 50, 500, 500);
//		this.setContentPane(panel);
//		pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setAlwaysOnTop(true);
	}
	
	public void addAgent(Agent a){
		
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
