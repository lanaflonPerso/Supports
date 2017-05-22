package JavaLesson;

import java.util.ArrayList;

import JavaLessonStudentCode.Personne;

/**
 * Hello world!
 *
 */
public class App 
{
	private static void print_tab( int tab[] )
	{
		System.out.print("[");
		for(int i = 0; i < tab.length ; ++i)
		{
			if( i != 0 ) System.out.print(", ");
			System.out.print(tab[i]);
		}
		System.out.println("]");
	}
	
	private static void print_tab( double tab[] )
	{
		System.out.print("[");
		for(int i = 0; i < tab.length ; ++i)
		{
			if( i != 0 ) System.out.print(", ");
			System.out.print(tab[i]);
		}
		System.out.println("]");
	}
	
	private static <T> void print_tab( T tab[] )
	{
		System.out.print("[");
		for(int i = 0; i < tab.length ; ++i)
		{
			if( i != 0 ) System.out.print(", ");
			System.out.print(tab[i]);
		}
		System.out.print("]\n");
	}
	
	public static void main5( String[] args )
    {
		int[] tab = new int[]{1,2,3,4,5};
    	System.out.println(tab);
    	
		String a = "12";
		String b = "2";
		System.out.println(a.compareTo(b));
	

		Integer a2 = 12;
		Integer b2 = 2;
		System.out.println(b2.compareTo(a2));
    }
	
    public static void main3( String[] args )
    {
    	Integer a = new Integer(5);
    	Integer b = new Integer(5);
    	
    	System.out.println(a == b);
    	System.out.println(a.equals(b));
    	
    	Integer a1 = 5;
    	Integer b1 = 5;
    	
    	System.out.println(a1 == b1);
    	
    	Integer a2 = 499999 + 1;
    	Integer b2 = 500000;
    	
    	System.out.println("4 : " + (a2 == b2));
    	
    	int a3 = 499999 + 1;
    	int b3 = 500000;
    	
    	System.out.println("5 : " + (a3 == b3));
    }
    
    public static void main( String[] args )
    {
    	int[] tab = null;
    	System.out.println(tab);
    	tab[(int)3.0] = 16;
    	for(int elt : tab)
    	{
    		System.out.println(elt);
    		elt = 5;
    	}
    	
    	for(Object elt : new ArrayList<Integer>())
    	{
    		System.out.println(elt);
    		elt = 5;
    	}
    	
    	print_tab(tab);
    	
    	for(int i = 0; i < tab.length ; ++i)
		{
    		tab[i] = 5;
		}
    	
    	print_tab(tab);
    	
    	int a1 = 0;
    	f1(a1);
    	
    	int[] a2 = new int[]{0};
    	f2(a2);
    	
    	int[] a3 = new int[]{0};
    	f3(a3);
    	
    	System.out.println("a1 : " + a1);
    	System.out.println("a2 : " + a2[0]);
    	System.out.println("a3 : " + a3[0]);
    }
    
    public static void f1(int a)
    {
    	a = 5;
    }
    
    public static void f2(int[] a)
    {
    	a = new int[]{5};
    }
    
    public static void f3(int[] a)
    {
    	a[0] = 5;
    }
}






