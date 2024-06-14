package io.keepcoding.keeptrivial.javacero;

import java.util.HashSet;
import java.util.Set;

public class Team 
{
    private String name;
    private Set<String> quesitos;

    public Team(String name) 
    {
        this.name = name;
        this.quesitos = new HashSet<>();
    }

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

    public boolean hasAllQuesitos() 
    {
        return quesitos.size() == 5; 
    }
}

