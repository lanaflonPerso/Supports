import random

prenoms = []
noms    = []

def numero_secu_aleatoire():
    return (
            str(random.randint(1,2)) +
            "".join( str(random.randint(0,9)) for _ in range(4) ) +
            str(random.randint(1,95)).rjust(2, "0") + 
            "".join( str(random.randint(0,9)) for _ in range(8) ) )


with open( "all_autors.txt", 'r' ) as f:
    for it_line in f:
        it_split_line = it_line.strip().split(" ")
        if len( it_split_line ) == 1:
            continue
        prenoms.append( it_split_line[0] )
        noms.append( " ".join( it_split_line[1:] ) )

with open( 'competences.txt', 'r') as f:
    competences = [it.strip() for it in f]

personne_id = 0

with open("personnes.csv", 'w') as f:
    f.write("id;numeroSecuriteSociale;nom;prenom\n")
    for it in range(5000):
        f.write( "{0};{1};{2};{3}\n".format(
            personne_id,
            numero_secu_aleatoire(),
            random.choice( noms ),
            random.choice( prenoms ) ) )

        personne_id += 1

def generer_personnes_avec_competences( filename, nb, nb_competences_max ):
    global personne_id
    with open(filename, 'w') as f:
        f.write("id;numeroSecuriteSociale;nom;prenom;competences\n")
        for it in range(nb):
            f.write( "{0};{1};{2};{3};{4}\n".format(
                personne_id,
                numero_secu_aleatoire(),
                random.choice( noms ),
                random.choice( prenoms ),
                ",".join( random.sample( competences, random.randint(1,nb_competences_max) ) ) ) )
            
            personne_id += 1

generer_personnes_avec_competences("formateurs.csv", 50, 5)
generer_personnes_avec_competences("personneEnReconversions.csv", 1000, 2)


