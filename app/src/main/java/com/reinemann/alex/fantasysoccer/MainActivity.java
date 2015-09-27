package com.reinemann.alex.fantasysoccer;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public Hashtable<String, SoccerTeam> teams = new Hashtable<>();
    private int numTeams;

    private String currentPlayer;
    private String currentTeam;

    private Button bIncGoals;
    private Button bIncAssists;
    private Button bIncShots;
    private Button bIncSaves;
    private Button bIncFouls;
    private Button bIncYelCards;
    private Button bIncRedCards;
    private Button bAddTeam;
    private Button bAddPlayer;
    private Button bPosition;
    private Button bRemoveTeam;
    private Button bRemovePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bIncGoals = (Button) findViewById(R.id.bIncreaseGoals);
        bIncAssists =  (Button) findViewById(R.id.bIncreaseAssists);
        bIncSaves = (Button) findViewById(R.id.bIncreaseSaves);
        bIncShots = (Button) findViewById(R.id.bIncreaseShots);
        bIncFouls = (Button) findViewById(R.id.bIncreaseFouls);
        bIncYelCards = (Button) findViewById(R.id.bIncreaseYellowCards);
        bIncRedCards = (Button) findViewById(R.id.bIncreaseRedCards);
        bAddTeam = (Button) findViewById(R.id.bAddTeam);
        bAddPlayer = (Button) findViewById(R.id.bAddPlayer);
        bPosition = (Button) findViewById(R.id.bChangePosition);
        bRemovePlayer = (Button) findViewById(R.id.bRemovePlayer);
        bRemoveTeam = (Button) findViewById(R.id.bRemoveTeam);

        bIncGoals.setOnClickListener(this);
        bIncAssists.setOnClickListener(this);
        bIncShots.setOnClickListener(this);
        bIncSaves.setOnClickListener(this);
        bIncFouls.setOnClickListener(this);
        bIncYelCards.setOnClickListener(this);
        bIncRedCards.setOnClickListener(this);
        bAddPlayer.setOnClickListener(this);
        bAddTeam.setOnClickListener(this);
        bPosition.setOnClickListener(this);
        bRemoveTeam.setOnClickListener(this);
        bRemovePlayer.setOnClickListener(this);


        teams.put("a", new SoccerTeam("a"));
        teams.put("b", new SoccerTeam("b"));
        teams.put("c", new SoccerTeam("c"));

        teams.get("a").addPLayer("alf", "big", 1, 1);
        teams.get("a").addPLayer("beta", "Small", 2, 2);
        teams.get("a").addPLayer("Sam", "Kev", 3, 2);
        teams.get("a").addPLayer("Trey", "Pos", 4, 4);

        currentPlayer = "bigalf";
        currentTeam = "a";

        numTeams = 3;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(bIncGoals.isPressed())
        {
            teams.get(currentTeam).players.get(currentPlayer).bumpGoals();
        }
        else if(bIncAssists.isPressed())
        {
            teams.get(currentTeam).players.get(currentPlayer).bumpAssists();
        }
        else if(bIncShots.isPressed())
        {
            teams.get(currentTeam).players.get(currentPlayer).bumpShots();
        }
        else if(bIncSaves.isPressed())
        {
            teams.get(currentTeam).players.get(currentPlayer).bumpSaves();
        }
        else if(bIncFouls.isPressed())
        {
            teams.get(currentTeam).players.get(currentPlayer).bumpFouls();
        }
        else if(bIncYelCards.isPressed())
        {
            teams.get(currentTeam).players.get(currentPlayer).bumpYellowCards();
        }
        else if(bIncRedCards.isPressed())
        {
            teams.get(currentTeam).players.get(currentPlayer).bumpRedCards();
        }
        else if(bPosition.isPressed())
        {
            int pos = teams.get(currentTeam).players.get(currentPlayer).getPositionNum();

            pos++;

            if(pos > 11)
            {
                pos = 1;
            }
            teams.get(currentTeam).players.get(currentPlayer).setPositionNum(pos);
        }
        else if(bAddTeam.isPressed())
        {
            numTeams++;
        }
        else if(bAddPlayer.isPressed())
        {

        }
        else if(bRemovePlayer.isPressed())
        {
            teams.get(currentTeam).removePlayer(currentPlayer);
        }
        else if(bRemoveTeam.isPressed())
        {
            if(numTeams != 0)
            {
                teams.remove(currentTeam);
                numTeams--;
            }
        }

    }
}
