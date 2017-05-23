import random
import datetime as dt

"""
private int id;
private OrganismeFormation organismeFormation;
private int duree;
private double coutHT;
private Set<Competence> competencesDeveloppees;!
"""

with open('formateurs.csv', 'r') as f:
    map_formateurs = {}
    for it_ln, it_l in enumerate(f):
        if it_ln == 0:
            continue
        it_split_ln = it_l.split(";")
        map_formateurs[ int(it_split_ln[0]) ] = it_split_ln[-1].strip().split(",") 

formateurs_key = list( map_formateurs.keys() )

with open( 'competences.txt', 'r') as f:
    competences = [it.strip() for it in f]

nb_organismes_formation = 83

nb_formations = 200

liste_formations = []

def write_formation_file(nb_formations):
    with open("formations.csv", 'w') as f:
        f.write("id;organismeFormation;duree;coutHT;competences\n")
        for id_ in range( 1, nb_formations + 1 ):
            if random.randint(1,3) == 1:
                id_formateur = -1
                competence = random.choice( competences )
            else:
                id_formateur = random.choice( formateurs_key )
                competence = random.choice( map_formateurs[ id_formateur ] )
            id_organisme = random.randint( 1, nb_organismes_formation )
            duree = random.randint(3,15)
            f.write( ";".join( map( str, (
                id_,
                id_organisme,
                duree,
                duree * random.randint(3, 12) * 100, competence ) ) ) )
            f.write("\n")
            liste_formations.append( (id_, duree) )

write_formation_file(300)

"""
private int id;
private Date dateDebut;
private Date dateFin;
private Formation formation;
private int capacite;
private List<Personne> inscrits;
"""

def write_session_formation_file():
    id_session_formation = 1
    with open("sessionFormations.csv", 'w') as f:
        f.write( "id;dateDebut;dateFin;formation;capacite\n" )
        for it_id_formation, it_duree in liste_formations:
            nb_sessions = random.randint(1,10)
            capacite = random.randint(6,12)
            date_fin = dt.date(2017,1,1)
            for it_session in range( nb_sessions ):
                date_debut = date_fin + dt.timedelta( days = random.randint(21,90) )
                date_fin = date_debut + dt.timedelta( days = it_duree - 1 )
                f.write( ";".join( map( str, (
                    id_session_formation,
                    date_debut.isoformat(),
                    date_fin.isoformat(),
                    it_id_formation,
                    capacite
                    ) ) ) )
                f.write("\n")
                id_session_formation += 1
write_session_formation_file()


