package com.reinemann.alex.fantasysoccer;

import java.io.Serializable;

/**
 * Created by Alex on 9/26/2015.
 */
public class SoccerPlayer implements Serializable {

    // instance variables
    private String firstName; // first name
    private String lastName; // last name
    private int uniformNum; // uniform number
    private int positionNum;
    private int goalsScored; // goals scored
    private int assists; // assists
    private int shots; // shots
    private int fouls; // fouls committed
    private int saves; // saves made
    private int yellowCards; // yellow cards shown
    private int redCards; // red cards shown
    private int playerPic;
    private int currentPosition;
    private int active;

    /**
     * SoccerPlayer constructor.  The parameters are copied in. Remaining fields
     * are set to zero.
     *
     * @param first first name
     * @param position the position the player plays
     * @param last last name
     * @param uniform uniform number
     */
    public SoccerPlayer(String first, String last, int uniform, int position, int image) {
        firstName = first.trim();
        lastName = last.trim();
        uniformNum = uniform;
        positionNum = position;
        goalsScored = 0;
        assists = 0;
        shots = 0;
        fouls = 0;
        saves = 0;
        yellowCards = 0;
        redCards = 0;
        playerPic = image;
        currentPosition = -1;
        active = 0;
    }

    // "get methods"
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getUniform() {
        return uniformNum;
    }
    public int getGoals() {
        return goalsScored;
    }
    public int getAssists() {
        return assists;
    }
    public int getShots() {
        return shots;
    }
    public int getFouls() {
        return fouls;
    }
    public int getSaves() {
        return saves;
    }
    public int getYellowCards() {
        return yellowCards;
    }
    public int getRedCards() {
        return redCards;
    }
    public int getPositionNum()
    {
        return positionNum;
    }
    public String getName()
    {
        return lastName + firstName;
    }

    public int getPlayerPic()
    {
        return playerPic;
    }

    public int getCurrentPosition()
    {
        return currentPosition;
    }
    public int getActive()
    {
        return active;
    }

    // change uniform number to new one
    public void changeUniform(int newNumber) {
        uniformNum = newNumber;
    }

    // "bump" methods, which increment the various stat fields
    public void bumpGoals() {
        goalsScored++;
    }
    public void bumpAssists() {
        assists++;
    }
    public void bumpShots() {
        shots++;
    }
    public void bumpFouls() {
        fouls++;
    }
    public void bumpSaves() {
        saves++;
    }
    public void bumpYellowCards() {
        yellowCards++;
    }
    public void bumpRedCards() {
        redCards++;
    }
    public void setPositionNum(int position)
    {
        positionNum = position;
    }

    public void setPlayerPic(int image)
    {
        playerPic = image;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
    public void setActive(int newActive)
    {
        active = newActive;
    }

    public boolean equals(SoccerPlayer sp)
    {
        return getName().equals(sp.getName());
    }

}
