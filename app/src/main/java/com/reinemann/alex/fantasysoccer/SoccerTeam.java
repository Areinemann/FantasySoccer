package com.reinemann.alex.fantasysoccer;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Alex on 9/26/2015.
 */
public class SoccerTeam implements Serializable {

    protected   Hashtable<String,SoccerPlayer> players = new Hashtable<>();
    private     String teamName;
    private     int numPlayers;

    private     int numWins;
    private     int numLosses;
    private     int numDraws;

    private     Drawable teamPic;

    /**
     * SoccerTeam constructor.  The parameter is copied in. Remaining field
     * is set to zero.
     *
     * @param name the name of the team to create.
     */
    public SoccerTeam(String name, Drawable instImage)
    {
        numPlayers = 0;
        teamName = name;
        teamPic = instImage;
    }

    //Get methods

    public String getName()
    {
        return teamName;
    }

    public SoccerPlayer getPlayer(String key)
    {
        return players.get(key);
    }

    public int getNumPlayers()
    {
        return numPlayers;
    }

    public int getNumWins()
    {
        return numWins;
    }

    public int getNumLosses()
    {
        return numLosses;
    }

    public int getNumDraws()
    {
        return numDraws;
    }

    public SoccerPlayer getPlayer(int position)
    {
        int i = 0;
        SoccerPlayer hold = null;
        for(SoccerPlayer sp : players.values())
        {
            if(i == position)
            {
                hold = sp;
            }
            i++;
        }
        return hold;
    }

    public Drawable getTeamPic()
    {
        return teamPic;
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
    public boolean addPLayer(String first, String last, int uniform, int position)
    {

        String key = last.trim() + first.trim();

        if(players.containsKey(key))
        {
            return false;
        }
        else
        {
            players.put(key, new SoccerPlayer(first, last, uniform, position));

            numPlayers++;

            return true;
        }


    }

    public boolean addPlayer(SoccerPlayer sp)
    {
        String key = sp.getLastName() + sp.getFirstName();

        if(players.containsKey(key))
        {
            return false;
        }
        else
        {
            players.put(key, sp);

            numPlayers++;

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
    public boolean removePlayer(String key)
    {

        if(players.containsKey(key))
        {
            players.remove(key);
            numPlayers--;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void increaseWins()
    {
        numWins++;
    }

    public void increaseLosses()
    {
        numLosses++;
    }

    public void increaseDraws()
    {
        numDraws++;
    }

    public void setTeamPic(Drawable newPic)
    {
        teamPic = newPic;
    }


}
