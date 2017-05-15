import json

with open('reseau_formatted.json', 'r') as f:
    reseau = eval( f.read() )

print( reseau.keys() )

mysql_keywords = {'type', 'show'}

def extract_from_dict( d, d_name ):
    simple_properties  = set()
    complex_properties = set()

    for k, v in d.items():
        for sub_k, sub_v in v.items():
            if isinstance(sub_v, (list,dict)):
                complex_properties.add( sub_k )
            else:
                simple_properties.add( sub_k )

    if simple_properties.intersection( complex_properties ):
        print( "WARNING : complex and simples properties intersects" )
        print( simple_properties.intersection( complex_properties ) )

    simple_properties_as_list = sorted( list( simple_properties ) )
    sql_statement = "INSERT INTO {0}(id,{1}) VALUES({{0}},{{1}});\n".format( 
        d_name.upper(),
        ','.join( (it + "_" if it in mysql_keywords else it) for it in simple_properties_as_list) )

    print( sql_statement )

    with open( "to_mysql.sql", 'a' ) as f:
        for k_num, (k, v) in enumerate( d.items() ):
            sql_statement_formatted = sql_statement.format( k_num,
                ",".join( translate_to_sql( v, simple_properties_as_list ) ) ) 
            f.write( sql_statement_formatted )

def translate_to_sql( v, simple_properties_as_list ):
    res = []
    for it in simple_properties_as_list:
        if it not in v:
            res.append("NULL")
            continue
        if v[it] in (True,False):
            res.append( str(v[it]).lower() )
            continue

        try:
            float(v[it])
            res.append(v[it])
            continue
        except:
            pass

        res.append(repr(v[ it ]))
    return res

def create_ligne_arrets( reseau ):
    stations_num_to_id = {}
    stations_id_to_num = {}

    for id_, k in enumerate(reseau['stations']):
        stations_num_to_id[ k ]   = id_
        stations_id_to_num[ id_ ] = k

    with open( "to_mysql.sql", 'a' ) as f:
        for ligne_id, (key_ligne, ligne) in enumerate( reseau['lignes'].items() ):
            if 'arrets' not in ligne or ligne_id == 14:
                continue
            for number, station in enumerate( ligne['arrets'][0] ):
                f.write( "INSERT INTO LIGNES_STATIONS( ligne_id, station_id, ordre ) VALUES({0},{1},{2});\n".format(
                    ligne_id,
                    stations_num_to_id[station],
                    number ) )
            


with open( "to_mysql.sql", 'w' ) as f:
    f.write( """
DROP DATABASE IF EXISTS PlanMetro;
CREATE DATABASE PlanMetro;

USE PlanMetro;

CREATE TABLE LIGNES(
    id INT PRIMARY KEY,
    color VARCHAR(256),
    name VARCHAR(256),
    num INT,
    show_ BOOLEAN,
    type_ VARCHAR(256));

CREATE TABLE STATIONS(
    id INT PRIMARY KEY,
    commune VARCHAR(256),
    cp VARCHAR(256),
    isAmbiguous BOOLEAN,
    isHub BOOLEAN,
    lat FLOAT,
    lng FLOAT,
    nom VARCHAR(256),
    num INT,
    show_ BOOLEAN,
    type_ VARCHAR(256));
    
CREATE TABLE LIGNES_STATIONS(
    ligne_id INT,
    station_id INT,
    ordre INT,
    PRIMARY KEY(ligne_id,station_id),
    FOREIGN KEY (ligne_id) REFERENCES LIGNES(id),
    FOREIGN KEY (station_id) REFERENCES STATIONS(id) );
    \n\n""" ) 


    
for it_key in reseau:
    try:
        extract_from_dict( reseau[it_key], it_key )
    except:
        pass

create_ligne_arrets( reseau )

