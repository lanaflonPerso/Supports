package JavaLesson;

public class ExempleTypes {
	public static void exemples_types()
	{
		double a = 1.0;
		// 1e-15 (10 puissance moins 15) est la précision maximale
		// relative en informatique
		System.out.println( "a + 1e-15" );
		System.out.println(  a + 1e-15  );
		// L'opération suivante n'est pas prise en compte
		System.out.println( "a + 1e-16" );
		System.out.println(  a + 1e-16  );
		
		int b = 2000000000;
		// L'opération suivante affiche -2094967296
		System.out.println(  b + 200000000 );
		
	}
}
