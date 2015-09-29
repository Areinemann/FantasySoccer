package com.reinemann.alex.fantasysoccer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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


        bIncWins = (Button) findViewById(R.id.bIncreaseWins);
        bIncLosses =  (Button) findViewById(R.id.bIncreaseLosses);
        bIncDraws = (Button) findViewById(R.id.bIncreaseDraws);
        bViewPlayers = (Button) findViewById(R.id.bViewPlayers);
        bAddTeam = (Button) findViewById(R.id.bAddTeam);
        bRemoveTeam = (Button) findViewById(R.id.bRemoveTeam);
        bCycleTeam = (Button) findViewById(R.id.bCycleTeams);

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

      //  Drawable temp = g

        teams.put("a", new SoccerTeam("a",null));
        teams.put("b", new SoccerTeam("b",null));
        teams.put("c", new SoccerTeam("c",null));
        teams.put("d", new SoccerTeam("d",null));
        teamNames.add("a");
        teamNames.add("b");
        teamNames.add("c");
        teamNames.add("d");


        teams.get("a").addPLayer("alf", "big", 1, 1);
        teams.get("a").addPLayer("beta", "Small", 2, 2);
        teams.get("a").addPLayer("Sam", "Kev", 3, 2);
        teams.get("a").addPLayer("Trey", "Pos", 4, 4);
        teams.get("b").addPLayer("alf", "big", 1, 1);

        currentTeam = "a";
        currentPosition = 0;

        numTeams = 3;

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
            String newName = etNewTeamName.getText().toString();
            teams.put(newName,new SoccerTeam(newName,null));
            teamNames.add(newName);
            numTeams++;
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

        imTeamPic.setImageDrawable(teams.get(currentTeam).getTeamPic());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
