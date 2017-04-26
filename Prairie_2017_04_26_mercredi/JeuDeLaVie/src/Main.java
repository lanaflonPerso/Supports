import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;


public class Main {
	private static final int largeur_pixel = 700, hauteur_pixel = 700;
	private static final int largeur_interne = 25, hauteur_interne = 25;
	private static final int proba_ini_numerateur = 1, proba_ini_denominateur = 5;
	
	private static void drawGrid()
	{
		for(int y = 0; y <= hauteur_interne + 1; ++y)
		{
			StdDraw.line(0, y, largeur_interne +  1, y);
		}
		
		for(int x = 0; x <= largeur_interne + 1; ++x)
		{
			StdDraw.line(x, 0, x, hauteur_interne + 1);
		}
	}
	
	private static boolean[][] obtenir_grille_aleatoire()
	{
		boolean[][] grille = new boolean[largeur_interne][hauteur_interne];
		Random random = new Random();
		for(int x = 0; x < largeur_interne; ++x)
		{
			for(int y = 0; y < hauteur_interne; ++y)
			{
				grille[x][y] = random.nextInt(proba_ini_denominateur - 1) < proba_ini_numerateur;
			}
		}
		return grille;
	}
	
	private static void drawCellules(boolean[][] grille) {
		StdDraw.setPenColor(StdDraw.BLUE);
		for(int x = 0; x < largeur_interne; ++x)
		{
			for(int y = 0; y < hauteur_interne; ++y)
			{
				if(grille[x][y])
				{
					StdDraw.filledCircle(x+0.5, y+0.5, 0.4);
				}
			}
		}	
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	
	private static int compter_voisins( boolean[][] grille, int x, int y)
	{

		return -1;
	}
	
	private static boolean[][] faireEvoluerCellules(boolean[][] grille) {
		return grille;
	}
	
	public static void main(String[] args)
	{
		StdDraw.setCanvasSize(largeur_pixel, hauteur_pixel);
		StdDraw.setXscale(-1, largeur_interne + 2);
		StdDraw.setYscale(-1, hauteur_interne + 2);
		StdDraw.enableDoubleBuffering();
		
		boolean[][] grille = obtenir_grille_aleatoire(); 
		
		for(int i = 0; i < 1000000 ; ++i)
		{
			StdDraw.clear();
			drawGrid();
			drawCellules( grille );
			grille = faireEvoluerCellules(grille);
			
			StdDraw.show();
			StdDraw.pause(400);
		}
	}
}
