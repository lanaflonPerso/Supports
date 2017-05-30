

public class DistanceLatitudeLongitude {
	public static final double rayonTerre = 6370;
	
	public static void main(String[] args) {
		System.out.println(distanceKilometriqueDepuisDegre(
				45,
				0,
				45,
				45
				));
	}
	
	public static double distanceAngulaireEnRadian(
			double latitude1,
			double longitude1,
			double latitude2,
			double longitude2 )
	{
		return Math.acos(
				Math.sin(latitude1)*Math.sin(latitude2)
			  + Math.cos(latitude1)*Math.cos(latitude2)*Math.cos(longitude1 - longitude2)
				);
	}
	
	public static double distanceKilometriqueDepuisRadian(
			double latitude1,
			double longitude1,
			double latitude2,
			double longitude2 )
	{
		return rayonTerre*distanceAngulaireEnRadian(latitude1, longitude1, latitude2, longitude2 );
	}
	
	public static double distanceKilometriqueDepuisDegre(
			double latitude1,
			double longitude1,
			double latitude2,
			double longitude2 )
	{
		double coef = Math.PI/180;
		return distanceKilometriqueDepuisRadian(
				latitude1  * coef, 
				longitude1 * coef, 
				latitude2  * coef, 
				longitude2 * coef );
	}

}
