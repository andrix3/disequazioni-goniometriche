
public class CalcoloAngolo {
	private int segno;	//0: >;  1: <;  2: =
	private int funzione;	//0: sin; 1: cos; 2: tg
	
	private double esprVal;
	
	private double gradi1;
	private double gradi2;
	private double rad1;
	private double rad2;
	
	private double g1;
	private double g2;

	private double x1, x2, y1, y2;

	private final float PI_GRECO = (float)3.141592654;
	
	private double fuori = -9999;		//-9999: ok; altrimenti no: prende il valore dato in input
	
	
	
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
		
		if((val > 1 || val < -1) && funzione != 2) {
			fuori = val;
			gradi1 = 0;
		}else {
			fuori = -9999;
			if(funzione == 0) 			//sin
				alfaRad = Math.asin(val);
			else if(funzione == 1) 		//cos
				alfaRad = Math.acos(val);
		}		
		
		if(funzione == 2) 		//tang
			alfaRad = Math.atan(val);
		
		//aRad : aGrad = 3.14 : 180
		//val : x = 3.14 : 180
		gradi1 = (alfaRad * 180) / PI_GRECO;
		
		if(gradi1 < 0)
			gradi1 = 360 + gradi1;
		
		gradi1 = unGiro(gradi1);
	}
	
	public void setGradi2(double val, int funzione) {
		
		if(fuori == -9999) {
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
		}else {
			gradi2 = 0;
		}
		
	}
	
	public double getGradi1() {
		return Math.round(gradi1 * 100.0) / 100.0;
	}
	public double getGradi2() {
		return Math.round(gradi2 * 100.0) / 100.0;
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
		return Math.round(rad1 * 100.0) / 100.0;
	}
	
	public double getRadianti2() {
		return Math.round(rad2 * 100.0) / 100.0;
	}
	
	private double getRadianti(double rad) {
		return Math.round(rad * 100.0) / 100.0;
	}
	
	public void setX1(double rad, int f) {
		if(f != 2)
			x1 = Math.cos(rad);
		else 
			x1 = 1;
		
		if(fuori != -9999) {
			if(f == 1) {
				x1 = fuori;
			}
		}
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
		
		if(fuori != -9999) {
			if(f == 0) {
				y1 = fuori;
			}
		}
	}
	public void setY2(double rad, int f) {
		if(f != 2)
			y2 = Math.sin(rad);
		else
			y2 = -Math.tan(rad);
	}
	
	public double getX1() {
	    return Math.round(x1 * 100.0) / 100.0;
	}
	public double getX2() {
		return Math.round(x2 * 100.0) / 100.0;
	}
	public double getY1() {
		return Math.round(y1 * 100.0) / 100.0;
	}
	public double getY2() {
		return Math.round(y2 * 100.0) / 100.0;
	}
	
	public String getGradiString() {
		if(segno == 0) {	//maggiore
			if(fuori != -9999) {
				if(fuori > 0) {
					setG1(0);
					setG2(0);
					return "Impossibile";
				}else {
					setG1(0);
					setG2(360);
					return "∀ x ∉ R";
				}
			}
			
			if(funzione == 0) {		//se seno
				if(getGradi1() <= 180) {
					if(getGradi1() == 90) {
						setG1(0);
						setG2(0);
						return "Impossibile";
					}
					setG1(getGradi1());
					setG2(getGradi2());
					return getGradi1() + "° +k360° < x < " + getGradi2() + "° +k360°";
				}
				else 
				{
					setG1(getGradi2() - 360);
					setG2(getGradi1());
					return Double.toString(getGradi2() - 360) + "° +k360° < x < " + getGradi1() + "° +k360°";
				}
			}else if(funzione == 1) {		//coseno
				if(getGradi1() <= 180) {
					if(getGradi1() == 0)
					{
						setG1(0);
						setG2(0);
						return "Impossibile";
					}
					setG1(getGradi2() - 360);
					setG2(getGradi1());
					return Double.toString(getGradi2() - 360) + "° +k360° < x < " + getGradi1() + "° +k360°";
				}else 
				{
					setG1(getGradi1());
					setG2(getGradi2());
					return getGradi1() + "° +k360° < x < " + getGradi2() + "° +k360°";
				}
			}else	//tang
			{
				if(getGradi1() < 90 || (getGradi1() >= 180 && getGradi1() < 270)) {
					setG1(getGradi1());
					setG2(90);
					return getGradi1() + "° +k180° < x < 90.0 +k180°";
				}else {
					setG1(getGradi1());
					setG2(270);
					return getGradi1() + "° +k180° < x < 270.0 +k180°";
				}
			}
			
		}else if(segno == 1) {	//minore
			if(fuori != -9999) {
				if(fuori < 0) {
					setG1(0);
					setG2(0);
					return "Impossibile";
				}else {
					setG1(0);
					setG2(360);
					return "∀ x ∉ R";
				}
			}
			
			if(funzione == 0) {		//seno
				if(getGradi1() <= 180)
				{
					setG1(getGradi2());
					setG2(getGradi1() + 360);
					return getGradi2() + "° +k360° < x < " + Double.toString(getGradi1() + 360) + "° +k360°";
				}
				else {
					if(getGradi1() == 270)
					{
						setG1(0);
						setG2(0);
						return "Impossibile";
					}
					setG1(getGradi1());
					setG2(getGradi2());
					return getGradi1() + "° +k360° < x < " + getGradi2() + "° +k360°";
				}
			
			}else if(funzione == 1) {		//coseno
				if(getGradi1() <= 180 && getGradi1() != 0) {
					if(getGradi1() == 180)
					{
						setG1(0);
						setG2(0);
						return "Impossibile";
					}
					setG1(getGradi1());
					setG2(getGradi2());
					return getGradi1() + "° +k360° < x < " + getGradi2() + "° +k360°";
				}
				else 
				{
					setG1(getGradi2());
					setG2(getGradi1() + 360);
					return getGradi2() + "° +k360° < x < " + Double.toString(getGradi1() + 360) + "° +k360°";
				}
			}else {		//tan
				if(getGradi1() < 90 || (getGradi1() >= 180 && getGradi1() < 270)) {
					setG1(90);
					setG2(getGradi2());
					return "90.0° +k180° < x < " + getGradi2() + "° +k180°";
				}else {
					setG1(90);
					setG2(getGradi1());
					return "90.0° +k180° < x < " + getGradi1() + "° +k180°";
				}
			}
		}else {		//uguale
			if(fuori != -9999) {
				return "Impossibile";
			}
			
			if(getGradi1() != getGradi2())
			{
				if(funzione != 2) {
					setG1(0);
					setG2(0);
					return getGradi1() + "° +k360° ∧ " + getGradi2() + "° +k360°";
				}else {
					setG1(0);
					setG2(0);
					return getGradi1() + "° +k180° ∧ " + getGradi2() + "° +k180°";
				}
			}
			else {
				if(funzione != 2) {
					setG1(0);
					setG2(0);
					return Double.toString(getGradi1()) + "° +k360°";
				}else {
					setG1(0);
					setG2(0);
					return Double.toString(getGradi1()) + "° +k180°";
				}
				
			}
		}
	}
	
	public String getRadiantiString() {
		if(segno == 0) {	//maggiore
			if(fuori != -9999) {
				if(fuori > 0) {
					return "Impossibile";
				}else {
					return "∀ x ∉ R";
				}
			}
			
			if(funzione == 0) {		//se seno
				if(getRadianti1() <= 3.14) {
					if(getRadianti1() == 1.57) {
						return "Impossibile";
					}
					return getRadianti1() + " +k6.28 < x < " + getRadianti2() + " +k6.28";
				}
				else 
					return getRadianti(getRadianti2() - 6.2) + " +k6.28 < x < " + getRadianti1() + " +k6.28";
			}else if(funzione == 1) {		//coseno
				if(getRadianti1() <= 3.14) {
					if(getRadianti1() == 0)
						return "Impossibile";
					return getRadianti(getRadianti2() - 6.2) + " +k6.28 < x < " + getRadianti1() + " +k6.28";
				}else 
					return getRadianti1() + " +k6.28 < x < " + getRadianti2() + " +k6.28";
			}else	//tang
			{
				if(getRadianti1() < 1.57 || (getRadianti1() >= 3.14 && getRadianti1() < 4.71)) {
					return getRadianti1() + " +k3.14 < x < 1.57 +k3.14";
				}else {
					return getRadianti1() + " +k3.14 < x < 4.71 +k3.14";
				}
			}
			
		}else if(segno == 1) {	//minore
			if(fuori != -9999) {
				if(fuori < 0) {
					return "Impossibile";
				}else {
					return "∀ x ∉ R";
				}
			}
			
			if(funzione == 0) {		//seno
				if(getRadianti1() <= 3.14)
					return getRadianti2() + " +k6.28 < x < " + getRadianti(getRadianti1() + 6.2) + " +k6.28";
				else {
					if(getRadianti1() == 4.71)
						return "Impossibile";
					return getRadianti1() + " +k6.28 < x < " + getRadianti2() + " +k6.28";
				}
			
			}else if(funzione == 1) {		//coseno
				if(getRadianti1() <= 3.14 && getRadianti1() != 0) {
					if(getRadianti1() == 3.14)
						return "Impossibile";
					return getRadianti1() + " +k6.28 < x < " + getRadianti2() + " +k6.28";
				}
				else 
					return getRadianti2() + " +k6.28 < x < " + getRadianti(getRadianti1() + 6.2) + " +k6.28";
			}else {		//tan
				if(getRadianti1() < 1.57 || (getRadianti1() >= 3.14 && getRadianti1() < 4.71)) {
					return "1.57 +k3.14 < x < " + getRadianti2() + " +k3.14";
				}else {
					return "1.57 +k3.14 < x < " + getRadianti1() + " +k3.14";
				}
			}
		}else {		//uguale
			if(fuori != -9999) {
				return "Impossibile";
			}
			
			if(getRadianti1() != getRadianti2())
			{
				if(funzione != 2)
				{
					return getRadianti1() + " +k6.28 ∧ " + getRadianti2() + " +k6.28";
				}else {
					return getRadianti1() + " +k3.14 ∧ " + getRadianti2() + " +k3.14";
				}
			}
			else {
				if(funzione != 2)
				{
					return Double.toString(getRadianti1()) + " +k6.28";
				}else {
					return Double.toString(getRadianti1()) + " +k3.14";
				}
			}
		}
	}
	
	public double getGradoArco1() {
		return g1;
	}

	public void setG1(double g1) {
		this.g1 = g1;
	}

	public double getGradoArco2() {
		return g2;
	}

	public void setG2(double g2) {
		this.g2 = g2;
	}
	
	public boolean isFuori() {
		if(fuori == -9999) {
			return false;
		}else {
			return true;
		}
	}
}