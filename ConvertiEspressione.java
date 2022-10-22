
public class ConvertiEspressione {
	
	private double num = 0;
	private double den = 0;
	
	private int ope;		//segno di un membro -> 0: *; 1: +; 2: -;
	private int index;		//indice per lo scorrimento della stringa
	
	private StringBuilder nume = new StringBuilder();		//stringa di backup su cui si va a lavorare
	
	public StringBuilder getNume() {
		return nume;
	}
	
	public String getStringNume() {
		return nume.toString();
	}

	public void setNume(StringBuilder nume) {
		this.nume = nume;
	}
	
	public double getEspressione(String str) {
		String nume = "";
		StringBuilder nume2 = new StringBuilder();		//backup..
		String deno = "";
		StringBuilder deno2 = new StringBuilder();		//backup..
		
		str += " ";
		
		//conto quante divisioni ci sono
		int k = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '/')
				k++;
		}
		//separo numeratore e denominatore
		boolean st = false;
		int z = 0;
		for(int i = 0; i < str.length() - 1; i++) {
			if(str.charAt(i) == '/') 
				z++;
			if(z == k / 2 + 1)
				st = true;
			
			if(!st) 	//k != 0 && 
				nume = nume + str.charAt(i);
			
			if(st){
				deno = deno + str.charAt(i + 1);
			}
		}
		
		//creo stringhe di backup
		nume2.append(nume);
		deno2.append(deno);
		
		//System.out.println("NUM_STR: " + nume);
		//System.out.println("DENO_STR: " + deno);
		
		//verifico esistenza di altre frazioni
		boolean stato = false;
		for(int i = 0; i < nume.length(); i++) {
			if(nume.charAt(i) == '/') {
				num = getEspressione(nume);
				stato = true;
			}
		}
		if(!stato)		//se non ci sono frazioni
			num = getNumeratore(nume2);		//ricavo il valore del numeratore
		
		boolean stato2 = false;
		for(int i = 0; i < deno.length(); i++) {
			if(deno.charAt(i) == '/') {
				den = getEspressione(deno);
				stato2 = true;
			}
		}
		if(!stato2)		//se non ci sono frazioni
			den = getDenominatore(deno2);
		
		//System.out.println("NUMERATORE: " + num);
		//System.out.println("DENOMINATORE: " + den);
	
		if(den != 0 && den != 9999)
			return num / den;
		else {
			return num;
		}
	}
	
	private double getNumeratore(StringBuilder nume) {
		double d;
		
		//resetto numeratore
		num = 0;
		setNume(nume);
		
		//faccio i prodotti
		d = cercaProdotti(nume);
		while(d != 9999) {
			setNum(d);
			d = cercaProdotti(nume);
		}
		
		//faccio le somme
		index = 0;
		d = getValore(nume.toString(), index, nume);
		while(d != 9999) {
			setNum(d);
			d = getValore(nume.toString(), index, nume);
		}
		
		return getNum();
	}
	
	private double getDenominatore(StringBuilder deno) {
		double d;
		
		//resetto denominatore
		den = 0;
		
		//faccio i prodotti
		d = cercaProdotti(deno);
		
		while(d != 9999) {
			setDen(d);
			d = cercaProdotti(deno);
		}
		
		//faccio le somme
		index = 0;
		d = getValore(deno.toString(), index, deno);
		while(d != 9999) {
			setDen(d);
			d = getValore(deno.toString(), index, deno);
		}
		
		return getDen();
	}
	
	private double getValore(String val, int indice, StringBuilder nume2) {
		val += " ";
		String str = new String();
		str = "";
		
		int ope = 1;

		for(int i = indice; i < val.length(); i++) {	
			if(val.charAt(i) == '+' || val.charAt(i) == '-' || val.charAt(i) == '*') {				
				if(i != indice) 	//se siamo fuori dal primo carattere di controllo
					index = i;			//salva l'indice e riutilizzalo per la prossima ricorsione
				else 		//altrimenti leggi il segno 
					ope = setOpe(val.charAt(i), ope);
				if(str != "") 
					break;
				
			}else if(val.charAt(i) != ' '){
				str += val.charAt(i);
				nume2.setCharAt(i, ' ');
			}
		}
		
		if(str != "") 
			return getDoubleValore(str, ope);
		else
			return 9999;
	}
	
	private double getDoubleValore(String str, int ope) {		//2r2r2 non funziona
		if(str == null)
			return 9999;
		
		boolean radiceB = false;
		double radiceVal = 0;
		
		double valD = 0.00;		
		
		//verifico se esistono altri segni dentro la stringa
		int ope2 = 1;		//default
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '-') {
				if(ope2 == 1)
					ope2 = 2;
				else
					ope2 = 1;
				StringBuilder strB = new StringBuilder(str);
				strB.setCharAt(i, ' ');
				setNume(strB);
				str = getStringNume();
			}
		}
		
		//converto il carattere in un valore double
		for(int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
				case ' ': {
					if(radiceVal != 0 && valD != 0) {
						valD *= Math.sqrt(radiceVal);
						radiceVal = 0;
					}else if(radiceVal != 0 && valD == 0) {
						valD = Math.sqrt(radiceVal);
						radiceVal = 0;
					}
					radiceB = false;
					break;
				}
				case 'r':{		//se è presente una radice
					if(radiceB) {	//se sto già raccogliendo dati da radice e vedo un'altra radice accanto alla precedente le moltiplico
						if(radiceVal != 0 && valD != 0) {		//usare un metodo come su case ' '
							valD *= Math.sqrt(radiceVal);
							radiceVal = 0;
						}else if(radiceVal != 0 && valD == 0) {
							valD = Math.sqrt(radiceVal);
							radiceVal = 0;
						}
						radiceB = false;
					}
					radiceB = true;
					break;
				}
				default:{		//carattere numerico
					if(!radiceB) 
						valD = valD * 10 + Character.getNumericValue(str.charAt(i));
					else if(radiceB)
						radiceVal = radiceVal * 10 + Character.getNumericValue(str.charAt(i));
				}
			}
		}
		//assagno i valori delle radici se esistenti a valD
		if(radiceVal != 0 && valD != 0) 
			valD *= Math.sqrt(radiceVal);
		else if(radiceVal != 0 && valD == 0)
			valD = Math.sqrt(radiceVal);
		
		//se il segno è meno
		if(ope == 2)
			valD *= -1;
		if(ope2 == 2)
			valD *= -1;
		
		return valD;
	}
	
	private double cercaProdotti(StringBuilder nume2) {
		boolean assegnazione = false;
		double dd = 1;
		for(int i = 0; i < nume2.length(); i++) {
			if(nume2.charAt(i) == '*') {
				assegnazione = true;
				nume2.setCharAt(i, ' ');
				double d1 = getDoubleValore(getStringaPrima(nume2.toString(), i, nume2), ope);
				double d2 = getDoubleValore(getStringaDopo(nume2.toString(), i, nume2), ope);
				
				if(d1 == 9999)
					dd = d2 * dd;
				else if(d2 == 9999)
					dd = d1 * dd;
				else
					dd = d1 * d2;
			}if(assegnazione && (nume2.charAt(i) == '+' || nume2.charAt(i) == '-'))
				return dd;
		}
		if(assegnazione)
			return dd;
		
		return 9999;
	}
	
	private String getStringaDopo(String val, int indice, StringBuilder nume2) {
		String str = "";
		
		ope = 1;
		
		//scorro la stringa in maniera crescente fino ad un nuovo operatore
		for(int i = indice; i < val.length(); i++) {			
			if(val.charAt(i) == '+' || val.charAt(i) == '-' || val.charAt(i) == '*') {	
				if(ope != 1) 			//se hai già impostato l'operatore
					ope = setOpe(val.charAt(i), ope);
				if(i != indice && str != "") {	//se l'operatore lo leggi dopo il primo carattere
					index = i;
					break;
				}if(val.charAt(i) == '*') {
					str += val.charAt(i);
					nume2.setCharAt(i, ' ');
					setNume(nume2);
				}
				if(val.charAt(i) == '-') 
					str += val.charAt(i);
			}else if(val.charAt(i) != ' '){
				str += val.charAt(i);
				nume2.setCharAt(i, ' ');
				setNume(nume2);
			}
		}
		if(str != "") 
			return str;
		else 
			return null;
		
	}
	private String getStringaPrima(String val, int indice, StringBuilder nume2) {
		String str = "";
		
		ope = 1;
		
		//scorro la stringa in maniera discendente fino ad un nuovo operatore
		for(int i = indice; i >= 0; i--) {			
			if(val.charAt(i) == '+' || val.charAt(i) == '-' || val.charAt(i) == '*') {
				ope = setOpe(val.charAt(i), ope);
				if(i != indice) 	//se l'operatore lo leggi dopo il primo carattere
					break;
			}else if(val.charAt(i) != ' '){
				str += val.charAt(i);
				nume2.setCharAt(i, ' ');
				setNume(nume2);
			}
		}
		
		//inverto la stringa
		StringBuilder str2 = new StringBuilder(str);
		for(int i = 0; i < str.length(); i++)
			str2.setCharAt(i, str.charAt(str.length() - i - 1));
		
		if(str != "") 
			return str2.toString();
		else 
			return null;
		
	}
	
	private int setOpe(char c, int ope) {
		switch (c) {
			case '*': 
				ope = 0;
				break;
			
			case '+':
				ope = 1;
				break;
			
			case '-':
				ope = 2;
				break;
			
			default:
				ope = 0;
				break;
			
		}
		return ope;
	}
	
	private void setNum(double d) {
		num += d;
	}
	
	private double getNum() {
		return num;
	}
	
	private void setDen(double d) {
		den += d;
	}
	
	private double getDen() {
		return den;
	}
}