package io.keepcoding.keeptrivial.javacero;

import java.util.HashSet;
import java.util.Set;

public class Team 
{
	//Atributos privados
    private String name;
    private Set<String> quesitos;

    public Team(String name) 
    {
    	//Constructor
        this.name = name;
        this.quesitos = new HashSet<>();
    }

    //Método getter
    public String getName() 
    {
        return name;
    }

    public Set<String> getQuesitos() 
    {
        return quesitos;
    }

    public void addQuesito(String quesito) 
    {
        quesitos.add(quesito);
    }

    //Método para operar sobre los atributos
    public boolean hasAllQuesitos() 
    {
        return quesitos.size() == 5; 
    }
}

