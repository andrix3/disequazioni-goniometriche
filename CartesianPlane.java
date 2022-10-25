
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class CartesianPlane extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private boolean procedo;
	
	private int segno;
	private int funzione;
	
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	
	private double gradoArco1, gradoArco2;
	
	private boolean fuori;
	
	public void setProcedo(boolean procedo) {
		this.procedo = procedo;
		//System.out.println(procedo);
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		super.setBounds(290, 0, 600, 400);
		g.setColor(Color.BLACK);
		g.drawLine(300, 0, 300, 400);
		g.drawLine(0, 200, 600, 200);
		g.drawOval(250, 150, 100, 100);
		g.drawString("1", 310, 150);
		g.drawString("-1", 310, 260);
		g.drawString("-1", 235, 200);
		g.drawString("1", 355, 200);
		
		if(procedo) {
			System.out.println("ciao");
			if(funzione == 1) {
				if(!fuori) {
					g.drawLine((int)300, 200, (int)(300 + (double)(50 * x1)), (int)(200 + (double)(50 * y1)));
					g.drawLine((int)300, 200, (int)(300 + (double)(50 * x1)), (int)(200 + (double)(50 * y2)));
				}
				g.setColor(Color.red);
				g.drawLine((int)((int)(300 + (double)(50 *  x1))), 0, (int)(300 + (double)(50 * x1)), 400);
				
				int x = segno;
				switch(x) {
				case 0:
					g.drawArc(250, 150, 100, 100, (int)gradoArco1, (int)gradoArco2 - (int)gradoArco1);
					
					break;
				case 1: 
					g.drawArc(250, 150, 100, 100, (int)gradoArco1, (int)gradoArco2 - (int)gradoArco1);
					break;
				case 2: 
					break;
				default: 
					System.err.println("errore switch op cp");
					break;
				}
			}
			else if(funzione == 0) {
				//System.out.println("41: " + (int)(200 - (double)(50 * Frame.getY1())));
				//System.out.println("41: " + (int)(200 - (double)(50 * Frame.getY1())));
				if(!fuori) {
					g.drawLine((int)300, 200, (int)(300 + (double)(50 * y1)), (int)(200 - (double)(50 * y1)));
					g.drawLine((int)300, 200, (int)(300 + (double)(50 * x2)), (int)(200 - (double)(50 * y1)));
				}
				g.setColor(Color.red);
				g.drawLine(0, (int)(200 - (double)(50 * y1)), 600, (int)(200 - (double)(50 * y1)));
				
				int x = segno;
				
				switch(x) {
				case 0:
					System.out.println("ciao");
					g.drawArc(250, 150, 100, 100, (int)gradoArco1, (int)gradoArco2 - (int)gradoArco1);
					break;
				case 1: 
					g.drawArc(250, 150, 100, 100, (int)gradoArco1, (int)gradoArco2 - (int)gradoArco1);	//180 + 2 * (int)
					break;
				case 2: 
					break;
				default: 
					System.err.println("errore switch op cp");
					break;
				}
			}
			else if(funzione == 2) {
				g.drawLine(350, 0, 350, 400);
				g.drawLine(0, (int)(200 - (double)(50 * y1)), 600, (int)(200 - (double)(50 * y1)));
				
				int x = segno;
				switch(x) {
				case 0:
					g.drawArc(250, 150, 100, 100, (int)gradoArco1, (int)gradoArco2 - (int)gradoArco1);
					break;
				case 1: 
					g.drawArc(250, 150, 100, 100, (int)gradoArco1, (int)gradoArco2 - (int)gradoArco1);	//180 + 2 * (int)
					break;
				case 2: 
					break;
				default: 
					System.err.println("errore switch op cp");
					break;
				}
				
				g.drawLine((int)(300 + (double)(50 * x1)), (int)(200 - (double)(50 * y1)), (int)(300 + (double)(50 * x2)), (int)(200 - (double)(50 * y2)));
			}
		}				
	}
	
	public void setAll(int segno, int funzione, boolean procedo, double x1, double x2, double y1, double y2, double gradoArco1, double gradoArco2, boolean fuori) {
		this.segno = segno;
		this.funzione = funzione;
		this.procedo = procedo;
		this.x1 = x1;
		this.x2 = x2;
		this.y2 = y2;
		this.y1 = y1;
		this.fuori = fuori;
		System.out.println(this.segno);
		System.out.println(this.funzione);
		System.out.println(this.procedo);
		System.out.println(this.x1);
		System.out.println(this.y1);
		System.out.println(this.x2);
		System.out.println(this.y2);
	}
}