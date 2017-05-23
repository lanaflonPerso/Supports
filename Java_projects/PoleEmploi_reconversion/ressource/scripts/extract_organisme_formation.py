import random

with open('organismeFormation.csv', 'w') as f_out:
    f_out.write("id;nom;numeroSiret;statut;responsable\n")
    nb = 1
    with open('/home/nicolas/Downloads/20170522_public_ofs.csv', 'r') as f:
        for it_ln_num, it_ln in enumerate( f ):
            if (it_ln_num == 0) or (random.randint(1,1000) != 1):
                continue
            split_line = it_ln.split(";")
            f_out.write( ";".join( (str(nb), split_line[0], split_line[1].replace('"',''), str( random.randint(1,4) ), str(nb) ) ) + "\n" )
            nb += 1

