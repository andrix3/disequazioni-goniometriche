import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame {
	
	private JFrame jf = new JFrame();
	private JButton button = new JButton("Fatto");
	private JLabel label1 = new JLabel();
	private JTextField label2 = new JTextField();
	private JLabel label3 = new JLabel();
	private JTextField label4 = new JTextField();
	private JLabel label5 = new JLabel();
	private JTextField label6 = new JTextField();
	private JPanel panel = new JPanel();
	private String[] optionsToChoose = {"Sin(x)", "Cos(x)", "Tan(x)"};
	private JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
	private JButton butPulisci = new JButton("Pulisci");

	private String[] optionsToChoose2 = {">", "<", "="};
	private JComboBox<String> jComboBox2 = new JComboBox<>(optionsToChoose2);
	private CartesianPlane plane = new CartesianPlane();
	
	private CalcoloAngolo ca = new CalcoloAngolo();
	
	private static double x1, x2, y1, y2;
	
	public static double getX1() {
		return x1;
	}

	public static double getX2() {
		return x2;
	}

	public static double getY1() {
		return y1;
	}

	public static double getY2() {
		return y2;
	}

	private static boolean pulito = false;
	
	public static boolean isPulito() {
		return pulito;
	}

	private String valore;
	private static int op;
	
	public static int getOp() {
		return op;
	}

	private static int funzione;
	
	public static int getFunzione() {
		return funzione;
	}

	public Frame(){
		// panel scelta funzione
		initFrame();
		initLabel();
		initButton();
		initComboBox();
		initPanel();
		plane.getClass();
		
	}
	
	private void initFrame() {// panel piano cartesiano
		jf.setTitle("piano cartesiano");
		jf.setSize(895, 450);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLayout(null);
		
		CartesianPlane c = new CartesianPlane();
		c.setBounds(260, 0, 600, 400);

		jf.add(c);
		jf.add(panel);
		jf.setVisible(true);
	}
	
	private void initLabel() {
		label1.setText("Scegli funzione goniometrica");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setEnabled(false);
		label1.setBounds(10,10,230,25);
		//label1.setBorder(BorderFactory.createLineBorder(Color.black));
			
		label2.setBounds(10, 80, 240, 25);
		label2.setBorder(BorderFactory.createLineBorder(Color.black));
		label2.setEnabled(true);
		
		label3.setText("Risultato in gradi");
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setEnabled(false);
		label3.setBounds(10,140,110,25);
		//label1.setBorder(BorderFactory.createLineBorder(Color.black));
			
		label4.setBounds(10, 165, 110, 25);
		label4.setBorder(BorderFactory.createLineBorder(Color.black));
		label4.setEnabled(false);
		
		label5.setText("Risultato in radianti");
		label5.setHorizontalAlignment(JLabel.CENTER);
		label5.setEnabled(false);
		label5.setBounds(130,140,120,25);
		//label1.setBorder(BorderFactory.createLineBorder(Color.black));
			
		label6.setBounds(130, 165, 120, 25);
		label6.setBorder(BorderFactory.createLineBorder(Color.black));
		label6.setEnabled(false);
	}   
	
	private void initButton() {
		button.setBounds(10, 115, 105, 25);
		ActionListener but = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//aspetto gaetano mi dica metodo
				// label2.getText();
				valore = label2.getText();
				System.out.println("bottone premuto\t" + op + "\t" + valore + "\t" + funzione);	
				// aspetto gaetano metodo 
				ca.setEspressione(valore, op , funzione);
				x1 = ca.getX1();
				y1 = ca.getY1();
				x2 = ca.getX2();
				y2 = ca.getY2();
				//label2.setText(ca.get);
				label4.setText(ca.getGradiString());
				label6.setText(ca.getRadiantiString());
				pulito = true;
				//CartesianPlane pl = new CartesianPlane();
				//plane = pl;
			}
		};
		button.addActionListener(but);
		
		butPulisci.setBounds(125, 115, 105, 25);
		ActionListener pulisci = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				valore = null;
				label4.setText("");
				label6.setText("");
				System.out.println("pulito");
				pulito = false;
			}
			
		};
		butPulisci.addActionListener(pulisci);
	}
	
	private void initPanel() {
		panel.setBounds(0,0, 260, 200);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setLayout(null);
		       
		panel.add(jComboBox);
		panel.add(jComboBox2);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label6);
		panel.add(button);
		panel.add(butPulisci);
	}
	
	private void initComboBox() {
		jComboBox.setBounds(10, 45, 115, 25);
		jComboBox2.setBounds(135, 45, 115, 25);
		
		jComboBox.addActionListener(new ActionListener() {  
	        public void actionPerformed(ActionEvent e) {       
	        	if(jComboBox.getItemAt(jComboBox.getSelectedIndex()).equalsIgnoreCase("Scegli funzione")) {
	        		
	        	}else {
		        	funzione = jComboBox.getSelectedIndex();  
		        }
	        }  
		});       
		
		jComboBox2.addActionListener(new ActionListener() {  
	        public void actionPerformed(ActionEvent e) {       
	        	//op = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());  
	        	//label6.setText(op);  
	        	op = jComboBox2.getSelectedIndex();
	        }  
		});     
	}
}
