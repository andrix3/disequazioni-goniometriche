
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;



public class CartesianPlane extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		super.setBounds(270, 0, 600, 400);
		g.setColor(Color.BLACK);
		g.drawLine(300, 0, 300, 400);
		g.drawLine(0, 200, 600, 200);
		g.drawOval(250, 150, 100, 100);
		g.drawString("1", 310, 150);
		g.drawString("-1", 310, 260);
		g.drawString("-1", 235, 200);
		g.drawString("1", 355, 200);
		
		if(Frame.isPulito()) {
			if(Frame.getFunzione() == 1) {
				g.drawLine((int)300, 200, (int)(300 + (double)(50 * Frame.getX1())), (int)(200 + (double)(50 * Frame.getY1())));
				g.drawLine((int)300, 200, (int)(300 + (double)(50 * Frame.getX1())), (int)(200 + (double)(50 * Frame.getY2())));
				g.setColor(Color.red);
				g.drawLine((int)((int)(300 + (double)(50 *  Frame.getX1()))), 0, (int)(300 + (double)(50 * Frame.getX1())), 400);
				
				int x = Frame.getOp();
				switch(x) {
				case 0:
					g.drawArc(250, 150, 100, 100, (int)CalcoloAngolo.getGradi2(), 2 * (int)CalcoloAngolo.getGradi1());
					break;
				case 1: 
					g.drawArc(250, 150, 100, 100, (int)CalcoloAngolo.getGradi1(), 360 - 2 * (int)CalcoloAngolo.getGradi1());
					break;
				case 2: 
					break;
				default: 
					System.err.println("errore switch op cp");
					break;
				}
			}
			else if(Frame.getFunzione() == 0) {
				//System.out.println("41: " + (int)(200 - (double)(50 * Frame.getY1())));
				//System.out.println("41: " + (int)(200 - (double)(50 * Frame.getY1())));
				g.drawLine((int)300, 200, (int)(300 + (double)(50 * Frame.getX1())), (int)(200 - (double)(50 * Frame.getY1())));
				g.drawLine((int)300, 200, (int)(300 + (double)(50 * Frame.getX2())), (int)(200 - (double)(50 * Frame.getY1())));
				g.setColor(Color.red);
				g.drawLine(0, (int)(200 - (double)(50 * Frame.getY1())), 600, (int)(200 - (double)(50 * Frame.getY1())));
				
				int x = Frame.getOp();
				switch(x) {
				case 0:
					g.drawArc(250, 150, 100, 100, (int)CalcoloAngolo.getGradoArco1(), (int)CalcoloAngolo.getGradoArco2() - (int)CalcoloAngolo.getGradoArco1());
					break;
				case 1: 
					g.drawArc(250, 150, 100, 100, (int)CalcoloAngolo.getGradoArco1(), (int)CalcoloAngolo.getGradoArco2() - (int)CalcoloAngolo.getGradoArco1());	//180 + 2 * (int)
					break;
				case 2: 
					break;
				default: 
					System.err.println("errore switch op cp");
					break;
				}
			}
			else if(Frame.getFunzione() == 2) {
				g.setColor(Color.RED);
				g.drawLine(350, 0, 350, 400);
				g.drawLine((int)(300 + (double)(50 * Frame.getX1())), (int)(200 - (double)(50 * Frame.getY1())), (int)(300 + (double)(50 * Frame.getX2())), (int)(200 - (double)(50 * Frame.getY2())));
			}
			
			System.out.println("x1: " + Frame.getX1());
			System.out.println("y1: " + Frame.getY1());
			System.out.println("x2: " + Frame.getX2());
			System.out.println("y2: " + Frame.getY2()); 
		}
		
		super.setVisible(true);
		
	}
}

