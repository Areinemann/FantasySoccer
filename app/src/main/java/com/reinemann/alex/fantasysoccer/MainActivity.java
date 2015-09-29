package com.reinemann.alex.fantasysoccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static Hashtable<String, SoccerTeam> teams = new Hashtable<>();
    private int numTeams;
    private ArrayList<String> teamNames;

    private String currentTeam;
    private int    currentPosition;

    private Button bIncWins;
    private Button bIncLosses;
    private Button bIncDraws;
    private Button bAddTeam;
    private Button bViewPlayers;
    private Button bRemoveTeam;
    private Button bCycleTeam;
    private Button bGame;

    private TextView tNumWins;
    private TextView tNumLosses;
    private TextView tNumDraws;
    private TextView tNumPlayers;
    private TextView tTeamName;
    private EditText etNewTeamName;

    private ImageView imTeamPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamNames = new ArrayList<>();


        bIncWins        = (Button) findViewById(R.id.bIncreaseWins);
        bIncLosses      =  (Button) findViewById(R.id.bIncreaseLosses);
        bIncDraws       = (Button) findViewById(R.id.bIncreaseDraws);
        bViewPlayers    = (Button) findViewById(R.id.bViewPlayers);
        bAddTeam        = (Button) findViewById(R.id.bAddTeam);
        bRemoveTeam     = (Button) findViewById(R.id.bRemoveTeam);
        bCycleTeam      = (Button) findViewById(R.id.bCycleTeams);
        bGame           = (Button) findViewById(R.id.bGame);

        tNumPlayers = (TextView) findViewById(R.id.teamPLayers);
        tNumWins = (TextView) findViewById(R.id.teamWins);
        tNumLosses = (TextView) findViewById(R.id.teamLosses);
        tNumDraws = (TextView) findViewById(R.id.teamDraws);
        tTeamName = (TextView) findViewById(R.id.teamName);
        etNewTeamName = (EditText) findViewById(R.id.newTeamName);

        imTeamPic = (ImageView) findViewById(R.id.teamPicture);


        bIncWins.setOnClickListener(this);
        bIncLosses.setOnClickListener(this);
        bIncDraws.setOnClickListener(this);
        bAddTeam.setOnClickListener(this);
        bViewPlayers.setOnClickListener(this);
        bRemoveTeam.setOnClickListener(this);
        bCycleTeam.setOnClickListener(this);
        bGame.setOnClickListener(this);

        teams.put("a", new SoccerTeam("a", R.drawable.uplogo));
        teams.put("b", new SoccerTeam("b", R.drawable.shield));
        teams.put("c", new SoccerTeam("c", R.drawable.uef));
        teams.put("d", new SoccerTeam("d", R.drawable.cybran));
        teamNames.add("a");
        teamNames.add("b");
        teamNames.add("c");
        teamNames.add("d");


        teams.get("a").addPlayer("alf", "big", 1, 1, R.drawable.alf);
        teams.get("a").addPlayer("beta", "Small", 2, 2, R.drawable.blue);
        teams.get("a").addPlayer("Sam", "Kev", 3, 2, 0);
        teams.get("a").addPlayer("Trey", "Pos", 4, 4, 0);
        teams.get("b").addPlayer("Monarch", "King", 1, 1, R.drawable.king);
        teams.get("b").addPlayer("Mad King", "Henry", 11, 11, R.drawable.henry);
        teams.get("b").addPlayer("Rhianne", "Burk", 2,2,R.drawable.burk);
        teams.get("c").addPlayer("Samantha", "Clark", 1, 1, R.drawable.clark);
        teams.get("c").addPlayer("Zachary", "Arnold", 2, 2, R.drawable.zach);
        teams.get("c").addPlayer("Allen", "Riley", 3,3,R.drawable.riley);
        teams.get("d").addPlayer("Gustaf", "Brackman", 1, 1, R.drawable.brackman);
        teams.get("d").addPlayer("Ivanna", "Dostya", 2, 2, R.drawable.dostya);
        teams.get("d").addPlayer("Gauge","", 4,4,R.drawable.gauge);

        currentTeam = "a";
        currentPosition = 0;

        numTeams = 4;

        fillTextFields();
        tTeamName.setText(currentTeam);

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

        if(bIncWins.isPressed())
        {
            teams.get(currentTeam).increaseWins();
        }
        else if (bCycleTeam.isPressed())
        {
            currentPosition++;
            if(currentPosition >= numTeams)
            {
                currentPosition = 0;
            }
            currentTeam = teamNames.get(currentPosition);
            tTeamName.setText(currentTeam);
        }
        else if(bIncLosses.isPressed())
        {
            teams.get(currentTeam).increaseLosses();
        }
        else if(bIncDraws.isPressed())
        {
            teams.get(currentTeam).increaseDraws();
        }
        else if(bViewPlayers.isPressed())
        {
            Intent intent = new Intent(MainActivity.this,PlayerViewActivity.class);
            for(int i = 0;i < numTeams; i++)
            {
                intent.putExtra(teamNames.get(i),teams.get(teamNames.get(i)));
            }
            intent.putExtra("Current", currentTeam);
            intent.putExtra("Team Array", teamNames);
            intent.putExtra("CurrentPos", currentPosition);
            intent.putExtra("NumTeams", numTeams);

            startActivityForResult(intent, 110);
        }
        else if(bAddTeam.isPressed())
        {
            if(!etNewTeamName.getText().toString().isEmpty())
            {
                String newName = etNewTeamName.getText().toString();
                teams.put(newName, new SoccerTeam(newName, 0));
                teamNames.add(newName);
                currentTeam = newName;
                currentPosition = numTeams;
                numTeams++;
            }
        }
        else if(bRemoveTeam.isPressed())
        {
            if(numTeams != 0) {
                int tempPos = currentPosition;
                String tempTeam = currentTeam;
                if (currentPosition < (numTeams - 1)) {
                    currentTeam = teamNames.get(currentPosition);
                }
                else if(currentPosition == 0)
                {
                    currentTeam = teamNames.get(currentPosition + 1);
                }
                else
                {
                    currentPosition = 0;
                    currentTeam = teamNames.get(currentPosition);
                }

                teams.remove(tempTeam);
                teamNames.remove(tempPos);
                numTeams--;
                tTeamName.setText(currentTeam);

            }
        }

        else if(bGame.isPressed())
        {
            Intent intent = new Intent(MainActivity.this,SoccerFieldActivity.class);

            startActivity(intent);
        }

        if(!bViewPlayers.isPressed())
        {
            fillTextFields();
        }
    }


    public void fillTextFields()
    {

        int temp = teams.get(currentTeam).getNumWins();
        tNumWins.setText("" + temp);
        temp = teams.get(currentTeam).getNumLosses();
        tNumLosses.setText("" + temp);
        temp = teams.get(currentTeam).getNumDraws();
        tNumDraws.setText("" + temp);
        temp = teams.get(currentTeam).getNumPlayers();
        tNumPlayers.setText(temp + " players");

        if(teams.get(currentTeam).getTeamPic() != 0)
        {
            imTeamPic.setImageResource(teams.get(currentTeam).getTeamPic());
        }
        else
        {
            imTeamPic.setImageResource(R.drawable.seriph);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 110)
        {
            if(resultCode == 112)
            {
                teams.clear();

                for(int i = 0; i < numTeams; i++)
                {
                    teams.put(teamNames.get(i), (SoccerTeam) data.getSerializableExtra(teamNames.get(i)));
                }
            }
        }
    }
}
