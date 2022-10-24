
public class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		new Frame();
		
		int segno = 0; 	//0: >;  1: <;  2: =
		int funzione = 0;	//0: sin; 1: cos; 2: tg
		String x = new String("1/2");
		
		CalcoloAngolo c = new CalcoloAngolo();
		
		//1
		c.setEspressione(x, segno, funzione);
		
		System.out.println(c.getGradi1());
		System.out.println(c.getGradi2());
		System.out.println(c.getRadianti1());
		System.out.println(c.getRadianti2());
		System.out.println();
		System.out.println(c.getX1());
		System.out.println(c.getY1());
		System.out.println(c.getX2());
		System.out.println(c.getY2());
		
		System.out.println();
		System.out.println();
		
		//2
		//segno = 0; 	//0: >;  1: <;  2: =
		//funzione = 1;	//0: sin; 1: cos; 2: tg
		//x = "r2/2";
		c.setEspressione(x, segno, funzione);
		
		System.out.println(c.getGradi1());
		System.out.println(c.getGradi2());
		System.out.println(c.getRadianti1());
		System.out.println(c.getRadianti2());
		System.out.println();
		System.out.println(c.getX1());
		System.out.println(c.getY1());
		System.out.println(c.getX2());
		System.out.println(c.getY2());
	}

}