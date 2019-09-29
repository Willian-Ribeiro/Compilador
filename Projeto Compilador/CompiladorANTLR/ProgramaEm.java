public class pEm{
	public static void main(String args[]){
		java.util.Scanner __SCAN_CU_MACRO__ = new java.util.Scanner(System.in);
		String a;
		String b;
		double c;
		double d;

		System.out.println("Este programa foi feito com o Compilador Universal");

		System.out.println("Digite o valor de a");

		a=__SCAN_CU_MACRO__.next();
		System.out.println("Digite o valor de b");

		b=__SCAN_CU_MACRO__.next();
		System.out.println("c = soma de a + b = ");

		c = 1 + 2;
		System.out.println(c);

		System.out.println("d = 2 * c = ");

		d = 2 * c;
		System.out.println(d);

		for( double iterador=3; iterador < 10; iterador += 1 ) {
			double j;
			double k;
			j=__SCAN_CU_MACRO__.next();
			j = 1 * 8.4;
			System.out.println(j);

			System.out.println("isto eh um loop");

			k=__SCAN_CU_MACRO__.next();
			k = 1 + -2;
			System.out.println(k);

			for( double iteradorNovo=5; iteradorNovo < 8; iteradorNovo += 1 ) {
			String f;
			f=__SCAN_CU_MACRO__.next();
			System.out.println("isto eh um loop aninhado");

			System.out.println(f);

		}

		}

		System.out.println("fim dos loops");

		if( a<b ) {
			System.out.println("isto eh um if");

		}

		if( 2<3 ) {
			System.out.println("isto eh um if");

		}
		else {
			System.out.println("isto eh um else");

		}

		while( d<2 ) {
			System.out.println("Isto eh um laco while");

			c = c + 1;
		}

	}
}
