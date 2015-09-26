package com.reinemann.alex.fantasysoccer;

import java.util.Hashtable;

/**
 * Created by Alex on 9/26/2015.
 */
public class SoccerTeam {

    protected   Hashtable<String,SoccerPlayer> players = new Hashtable<>();
    private     String teamName;
    private     int numPLayers;

    /**
     * SoccerTeam constructor.  The parameter is copied in. Remaining field
     * is set to zero.
     *
     * @param name the name of the team to create.
     */
    public SoccerTeam(String name)
    {
        numPLayers = 0;
        teamName = name;
    }

    //Get methods

    public String getName()
    {
        return teamName;
    }

    //TODO
    public SoccerPlayer getPlayer()
    {
        return null;
    }

    public int getNumPLayers()
    {
        return numPLayers;
    }

    /**
     * addPlayer adds a player to the teams hashtable
     *
     * @param first     First name of player
     * @param last      Last name of player
     * @param uniform   Uniform number of player
     *
     * @return          false if player already exists, true if player successfully added
     */
    public boolean addPLayer(String first, String last, int uniform)
    {

        String key = last.trim() + first.trim();

        if(players.containsKey(key))
        {
            return false;
        }
        else
        {
            players.put(key, new SoccerPlayer(first, last, uniform));
            numPLayers++;
            return true;
        }


    }

    /**
     * removePlayer removes a player for the hashtable
     *
     * @param first First name of player
     * @param last  Last name of player
     *
     * @return      true if player removed false if player not found
     */
    public boolean removePlayer(String first, String last)
    {
        String key = last.trim() + first.trim();

        if(players.containsKey(key))
        {
            players.remove(key);
            numPLayers--;
            return true;
        }
        else
        {
            return false;
        }
    }


}
