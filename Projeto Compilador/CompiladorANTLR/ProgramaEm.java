public class Main {
	public static void main(String args[]){
		java.util.Scanner __SCAN_CU_MACRO__ = new java.util.Scanner(System.in);
		String a;
		double b;
		double c;
		double d;
		String e;
		double f;
		double g;
		double count;

		System.out.println("Este programa foi feito com o Compilador Universal");

		System.out.println("Digite o texto de a");

		a=__SCAN_CU_MACRO__.next();
		System.out.println(a);

		System.out.println("Digite o valor de b");

		b=__SCAN_CU_MACRO__.nextDouble();
		c = 1 + 2 * 3.3 * 8.8 * 9 * 10 * 11.1 * 12 * 13 / 100000 + -(1);
		System.out.println("Valor de c");

		System.out.println(c);

		d = 1 - -44 * 3 * 8 * -9 + -(1 * 4 * 3 * 2 + b * +(1 + 2));
		System.out.println("Valor de d");

		System.out.println(d);

		e = "texto";
		System.out.println(e);

		System.out.println("Numero 5");

		System.out.println(5);

		System.out.println("f = soma de 1 + 2 + 3 + 4 ");

		f = 1 + 2 + 3 + 4;
		System.out.println(f);

		System.out.println("g = 2 * f * ( f + b ) = ");

		g = 2 * f * +(f + b);
		System.out.println(g);

		for( double iterador=1; iterador < 3; iterador += 1 ) {
			double j;
			System.out.println("Qual valor de j?");

			j=__SCAN_CU_MACRO__.nextDouble();
			j = j * 8.4;
			System.out.println("j = j * 8.5");

			System.out.println(j);

			System.out.println("isto eh um loop, repetindo");

			System.out.println(iterador);

			System.out.println("vezes");

			for( double iteradorNovo=5; iteradorNovo < 7; iteradorNovo += 1 ) {
			double l;
			System.out.println("Qual valor de l?");

			l=__SCAN_CU_MACRO__.nextDouble();
			System.out.println("isto eh um loop aninhado");

			System.out.println("l vale ");

			System.out.println(l);

		}

		}

		System.out.println("fim dos loops");

		System.out.println("se c de valor");

		System.out.println(c);

		System.out.println("maior que b de valor ");

		System.out.println(b);

		System.out.println("execute if");

		if( c>b ) {
			System.out.println("isto eh um if");

		}

		System.out.println("se 2>3 execute if");

		if( 2>3 ) {
			System.out.println("isto eh um if");

		}
		else {
			System.out.println("isto eh um else");

		}

		count = 0;
		System.out.println("Isto eh um laco while");

		while( count<10 ) {
			System.out.println("Repetindo enquanto d < 10");

			count=__SCAN_CU_MACRO__.nextDouble();
		}

		System.out.println("fim do programa");

	}
}
