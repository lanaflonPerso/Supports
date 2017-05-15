import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;


public class JeuDeDame {
	private static final int largeur_pixel = 700, hauteur_pixel = 700;
	private static final int largeur_interne = 8, hauteur_interne = 8;

	private static void drawGrid()
	{
		for(int y = 0; y < hauteur_interne; ++y)
		{
			for(int x = 0; x < largeur_interne; ++x)
			{
				if( (x + y) % 2 == 0 )
				{
					StdDraw.setPenColor(StdDraw.YELLOW);
				}
				else
				{
					StdDraw.setPenColor(StdDraw.BLACK);
				}
				StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
			}
		}
	}

	private static void drawCellules(int[][] grille) {

		for(int x = 0; x < largeur_interne; ++x)
		{
			for(int y = 0; y < hauteur_interne; ++y)
			{
				if(grille[x][y] == 1)
				{
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledCircle(x+0.5, y+0.5, 0.4);
				}
				if(grille[x][y] == 2)
				{
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledCircle(x+0.5, y+0.5, 0.4);
				}
			}
		}	
		StdDraw.setPenColor(StdDraw.BLACK);
	}

	private static int[][] obtenir_grille_depart() {
		int[][] grille = new int[largeur_interne][hauteur_interne];
		for(int y = 0; y < hauteur_interne; ++y)
		{
			for(int x = 0; x < largeur_interne; ++x)
			{
				if( (x + y) % 2 == 0 )
				{
					if( y < 3 )       grille[x][y] = 1;
					else if (y >= 5)  grille[x][y] = 2;
					else grille[x][y] = 0;
				}
			}
		}
		return grille;
	}

	private static boolean tour_du_joueur_blanc = true;
	private static int x_courant = -1, y_courant = -1;

	public static void main(String[] args)
	{
		StdDraw.setCanvasSize(largeur_pixel, hauteur_pixel);
		StdDraw.setXscale(-1, largeur_interne + 1);
		StdDraw.setYscale(-1, hauteur_interne + 1);
		StdDraw.enableDoubleBuffering();

		int[][] grille = obtenir_grille_depart(); 

		while(true)
		{
			StdDraw.clear();
			drawGrid();
			if( aucun_jeton_leve() )  verifier_leve_jeton(grille);
			if( !aucun_jeton_leve() ) verifier_depot_jeton(grille);
			
			drawCellules( grille );
			dessine_jeton_en_l_air();
			StdDraw.show();
			StdDraw.pause(40);
		}
	}

	private static void verifier_depot_jeton(int[][] grille) {
		if(StdDraw.mousePressed() && est_depot_valide(grille) )
		{
			int x_depot = (int)StdDraw.mouseX();
			int y_depot = (int)StdDraw.mouseY();
			grille[x_depot][y_depot] = tour_du_joueur_blanc ? 1 : 2;
			tour_du_joueur_blanc = !tour_du_joueur_blanc;
			x_courant = -1; y_courant = -1;
		}
	}

	private static void verifier_leve_jeton(int[][] grille) {
		if( StdDraw.mousePressed() && est_valide_pour_lever(grille) )
		{
			x_courant = (int)StdDraw.mouseX();
			y_courant = (int)StdDraw.mouseY();
			grille[x_courant][y_courant] = 0;
		}
	}

	private static boolean aucun_jeton_leve() {
		return x_courant == -1;
	}

	private static boolean est_depot_valide(int[][] grille) {
		int x_depot = (int)StdDraw.mouseX();
		int y_depot = (int)StdDraw.mouseY();
		if( x_depot < 0 || x_depot > 7 || y_depot < 0 || y_depot > 7 ) return false;
		if( grille[x_depot][y_depot] != 0 ) return false;
		if( (Math.abs(x_depot - x_courant)*Math.abs(y_depot - y_courant) != 1 ) ) return false;
		return true;
	}

	// Re
	private static boolean est_valide_pour_lever(int[][] grille)
	{
		int x = (int)StdDraw.mouseX();
		int y = (int)StdDraw.mouseY();

		if( grille[x][y] == 1 && tour_du_joueur_blanc)  return true;
		if( grille[x][y] == 2 && !tour_du_joueur_blanc) return true;
		return false;
	}

	private static void dessine_jeton_en_l_air() {
		if( ( x_courant == -1 ) && ( y_courant == -1 ) ) { return; }

		if(tour_du_joueur_blanc) StdDraw.setPenColor(StdDraw.WHITE);
		else StdDraw.setPenColor(StdDraw.BLACK);

		StdDraw.filledCircle(StdDraw.mouseX(), StdDraw.mouseY(), 0.4);
	}
}
