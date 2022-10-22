
public class CalcoloAngolo {
	private int segno;	//0: >;  1: <;  2: =
	private int funzione;	//0: sin; 1: cos; 2: tg
	
	private double esprVal;
	
	private static double gradi1;
	private static double gradi2;
	private double rad1;
	private double rad2;
	
	private double x1, x2, y1, y2;

	final float PI_GRECO = (float)3.141592654;
	
	public CalcoloAngolo(){
		
	}
	
	public void setEspressione(String x, int s, int f) {
		ConvertiEspressione e = new ConvertiEspressione();
		
		//imposto attributi
		this.esprVal = e.getEspressione(x);
		this.segno = s;
		this.funzione = f;
		
		//calcolo valori
		setGradi1(esprVal, funzione);
		setGradi2(getGradi1(), funzione);
		
		ordinaGradi();
		
		setRadianti1(gradi1);
		setRadianti2(gradi2);
		
		setX1(rad1, funzione);
		setY1(rad1, funzione);
		setX2(rad2, funzione);
		setY2(rad2, funzione);
	}
	
	public void setGradi1(double val, int funzione) {
		double alfaRad = 0.0;
		
		if(funzione == 0) 			//sin
			alfaRad = Math.asin(val);
		else if(funzione == 1) 		//cos
			alfaRad = Math.acos(val);
		else if(funzione == 2) 		//tang
			alfaRad = Math.atan(val);
		
		//aRad : aGrad = 3.14 : 180
		//val : x = 3.14 : 180
		gradi1 = (alfaRad * 180) / PI_GRECO;
		
		if(gradi1 < 0)
			gradi1 = 360 + gradi1;
		
		gradi1 = unGiro(gradi1);
	}
	
	public void setGradi2(double val, int funzione) {
		
		if(funzione == 0) {			//sin
			if(val > 180) 
				gradi2 = 360 - (val - 180);
			else
				gradi2 = 180 - val;
		}else if(funzione == 1) 	//cos
			gradi2 = 360 - Math.abs(val);
		else 						//tan
			gradi2 = 180 + val;		
		
		
		gradi2 = unGiro(gradi2);
	}
	
	public static double getGradi1() {
		return Math.round(gradi1*100.0)/100.0;
	}
	public static double getGradi2() {
		return Math.round(gradi2*100.0)/100.0;
	}
	
	private void ordinaGradi() {
		if(gradi2 < gradi1) {
			double g = gradi1;
			gradi1 = gradi2;
			gradi2 = g;
		}
	}
	
	private double unGiro(double gradi1) {
		if(gradi1 / 360 >= 1)
			gradi1 -= 360;
		
		return gradi1;
	}
	
	public void setRadianti1(double gradi) {
		this.rad1 = (gradi * PI_GRECO) / 180;
	}
	
	public void setRadianti2(double gradi) {
		this.rad2 = (gradi * PI_GRECO) / 180;
	}
	
	public double getRadianti1() {
		return Math.round(rad1*100.0)/100.0;
	}
	
	public double getRadianti2() {
		return Math.round(rad2*100.0)/100.0;
	}
	
	public void setX1(double rad, int f) {
		if(f != 2)
			x1 = Math.cos(rad);
		else 
			x1 = 1;
	}
	public void setX2(double rad, int f) {
		if(f != 2)
			x2 = Math.cos(rad);
		else 
			x2 = -1;
	}	
	public void setY1(double rad, int f) {
		if(f != 2)
			y1 = Math.sin(rad);
		else
			y1 = Math.tan(rad);
	}
	public void setY2(double rad, int f) {
		if(f != 2)
			y2 = Math.sin(rad);
		else
			y2 = -Math.tan(rad);
	}
	
	public double getX1() {
		//double num = 12.5249;
	    return Math.round(x1*100.0)/100.0;
		//return x1;
	}
	public double getX2() {
		return Math.round(x2*100.0)/100.0;
	}
	public double getY1() {
		return Math.round(y1*100.0)/100.0;
	}
	public double getY2() {
		return Math.round(y2*100.0)/100.0;
	}
	
	public String getGradiString() {
		if(segno == 0) {	//maggiore
			if(funzione == 0) {		//se seno
				if(getGradi1() != getGradi2())
					if(getGradi1() <= 180)
						return getGradi1() + " < x < " + getGradi2();
					else 
						return Double.toString(getGradi2() - 360) + " < x < " + getGradi1();
				else {
					if(getGradi1() != 90)
						return Double.toString(getGradi1());
					else {
						return "Impossibile";
					}
				}
			}else if(funzione == 1)		//coseno
			{
				if(getGradi1() != getGradi2())
					if(getGradi1() <= 180)
						return Double.toString(getGradi2() - 360) + " < x < " + getGradi1();
					else 
						return getGradi1() + " < x < " + getGradi2();
				else {
					if(getGradi1() != 0)
						return Double.toString(getGradi1());
					else {
						return "Impossibile";
					}
				}
			}else	//tang
			{
				
			}
			
		}else if(segno == 1) {	//minore
			if(funzione == 0) {		//seno
				if(getGradi1() != getGradi2())
					if(getGradi1() <= 180)
						return getGradi2() + " < x < " + Double.toString(getGradi1() + 360);
					else 
						return getGradi1() + " < x < " + getGradi2();
				else {
					if(getGradi1() != 270)
						return Double.toString(getGradi1());
					else {
						return "Impossibile";
					}
				}
			}else if(funzione == 1) {		//coseno
				if(getGradi1() != getGradi2())
					if(getGradi1() <= 180)
						return getGradi1() + " < x < " + getGradi2();
					else 
						return getGradi2() + " < x < " + Double.toString(getGradi1() + 360);
				else {
					if(getGradi1() != 180)
						return Double.toString(getGradi1());
					else {
						return "Impossibile";
					}
				}
			}else {		//tan
				
			}
			
		}else {		//uguale
			if(getGradi1() != getGradi2())
				return getGradi1() + " ∧ " + getGradi2();
			else {
				return Double.toString(getGradi1());
			}
		}
		
		return "";
	}
}