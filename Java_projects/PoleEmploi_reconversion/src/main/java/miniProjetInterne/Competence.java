package miniProjetInterne;

import java.util.*;

public class Competence implements Comparable<Competence> {
	private static Map<String,Competence> competenceByName = new TreeMap<String,Competence>();
	
	private String name;
	private int id;
	
	public static Competence getCompetenceByName(String name)
	{
		Competence competence =  competenceByName.get(name);
		if( competence == null )
		{
			competence = new Competence(name);
		}
		return competence;
	}
	
	private Competence(String name)
	{
		this.id = competenceByName.size();
		this.name = name;
		competenceByName.put(name, this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competence other = (Competence) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	@Override
	public int compareTo(Competence o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	
}
