package JavaLesson;

import JavaLessonStudentCode.Personne;

/**
 * Hello world!
 *
 */
public class App 
{
	private static long c;
	
	private static boolean getVrai()
	{
		boolean vrai = 2 == 2;
    	if(vrai) return vrai;
    	return false;
	}
	
    public static void main( String[] args )
    {
    	System.out.println(
    			System.getProperty("user.dir"));;
    			
    			
    	System.out.println("Debut\n\n\n\n\nFin");
    	ExempleTypes.exemples_types();
    	//int b = 123456789000000;
    	double pi = 3.141591567;
    	double age_de_l_univers = 15e15;
    	
    	System.out.println( 
    			((Long)c).toString().charAt(0) == '1' );
    }
}
