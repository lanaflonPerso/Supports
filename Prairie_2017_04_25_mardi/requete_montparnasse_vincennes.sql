## s1 est la station de depart, s2 la correspondance, s3 l arrivee
## l1 la première ligne
## l2 la deuxième

SELECT s1.nom, l1.num, ABS(ls1.ordre - ls2.ordre), 
       s2.nom, l2.num, ABS(ls3.ordre - ls4.ordre), 
       s3.nom,         ABS(ls1.ordre - ls2.ordre) + ABS(ls3.ordre - ls4.ordre) as nb_stations_total
                            ## On va de la station 1 à la station 2 en métro direct, elles doivent
                            ## donc être sur la même ligne                            
FROM LIGNES_STATIONS as ls1 JOIN LIGNES_STATIONS as ls2 ON ls1.ligne_id   = ls2.ligne_id 
                            ## On passe de la ligne-station 2 à la 3 par correspondance
                            ## donc c'est la même station
						    JOIN LIGNES_STATIONS as ls3 ON ls2.station_id = ls3.station_id
                            JOIN LIGNES_STATIONS as ls4 ON ls3.ligne_id   = ls4.ligne_id
                            JOIN STATIONS as s1 ON s1.id = ls1.station_id
                            JOIN STATIONS as s2 ON s2.id = ls2.station_id
                            JOIN STATIONS as s3 ON s3.id = ls4.station_id
                            JOIN LIGNES as l1 ON l1.id = ls1.ligne_id
							JOIN LIGNES as l2 on l2.id = ls4.ligne_id
WHERE s1.id = 104 AND s3.id = 245
ORDER BY nb_stations_total;
