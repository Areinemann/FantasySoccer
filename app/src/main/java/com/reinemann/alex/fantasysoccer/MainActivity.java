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

    private Hashtable<String, SoccerTeam> teams = new Hashtable<>();
    private int numTeams;

    private Button bIncGoals;
    private Button bIncAssists;
    private Button bIncShots;
    private Button bIncSaves;
    private Button bIncFouls;
    private Button bIncYelCards;
    private Button bIncRedCards;
    private Button bAddTeam;
    private Button bAddPlayer;


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

        bIncGoals.setOnClickListener(this);
        bIncAssists.setOnClickListener(this);
        bIncShots.setOnClickListener(this);
        bIncSaves.setOnClickListener(this);
        bIncFouls.setOnClickListener(this);
        bIncYelCards.setOnClickListener(this);
        bIncRedCards.setOnClickListener(this);
        bAddPlayer.setOnClickListener(this);
        bAddTeam.setOnClickListener(this);
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

    }
}
