package com.reinemann.alex.fantasysoccer;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class SoccerFieldActivity extends AppCompatActivity implements View.OnClickListener {

    //alertDialog

    private TextView        tTeam1,
                            tTeam2;
    private Button          bSwitchTeam1,
                            bSwitchTeam2,
                            bGoBack,
                            bPlay;
    private ImageButton     bKeeper1,
                            bKeeper2,
                            bSweep1,
                            bSweep2,
                            bWing1,
                            bWing2,
                            bCorner1,
                            bCorner2,
                            bFor1,
                            bFor2;

    private int     currentPosition1;
    private int     currentPosition2;
    private String  currentTeam1;
    private String  currentTeam2;

    private boolean team1Active[];
    private boolean team2Active[];

    private String  team1positions[] = new String[5];
    private String  team2positions[] = new String[5];

    ArrayList<String> team1 = new ArrayList<>();
    ArrayList<String> team2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer_field);

        Intent intent = getIntent();

        currentPosition1 = intent.getIntExtra("Current Pos", 0);
        currentTeam1    = intent.getStringExtra("Current Team");
        if(currentPosition1+1 >= MainActivity.numTeams)
        {
            currentPosition2=0;
        }
        else
        {
            currentPosition2 = currentPosition1 + 1;
        }
        currentTeam2    = MainActivity.teamNames.get(currentPosition2);


        tTeam1           = (TextView) findViewById(R.id.team1View);
        tTeam2           = (TextView) findViewById(R.id.team2View);
        bSwitchTeam1     = (Button) findViewById(R.id.bSwitchTeam1);
        bSwitchTeam2     = (Button) findViewById(R.id.bSwitchTeam2);
        bGoBack          = (Button) findViewById(R.id.goBack);
        bPlay            = (Button) findViewById(R.id.play);
        bKeeper1         = (ImageButton) findViewById(R.id.team1Keeper);
        bKeeper2         = (ImageButton) findViewById(R.id.team2Keeper);
        bSweep1          = (ImageButton) findViewById(R.id.team1Sweep);
        bSweep2          = (ImageButton) findViewById(R.id.team2Sweep);
        bWing1           = (ImageButton) findViewById(R.id.team1Wing);
        bCorner1         = (ImageButton) findViewById(R.id.team1Corner);
        bWing2           = (ImageButton) findViewById(R.id.team2Wing);
        bCorner2         = (ImageButton) findViewById(R.id.team2Corner);
        bFor1            = (ImageButton) findViewById(R.id.team1For);
        bFor2            = (ImageButton) findViewById(R.id.team2For);

        bSwitchTeam1.setOnClickListener(this);
        bSwitchTeam2.setOnClickListener(this);
        bGoBack.setOnClickListener(this);
        bPlay.setOnClickListener(this);

        bKeeper1.setOnClickListener(this);
        bKeeper2.setOnClickListener(this);
        bSweep1.setOnClickListener(this);
        bSweep2.setOnClickListener(this);
        bWing1.setOnClickListener(this);
        bWing2.setOnClickListener(this);
        bCorner1.setOnClickListener(this);
        bCorner2.setOnClickListener(this);
        bFor1.setOnClickListener(this);
        bFor2.setOnClickListener(this);

        bKeeper1.setMaxHeight(50);
        bKeeper2.setMaxHeight(50);
        bSweep1.setMaxHeight(50);
        bSweep2.setMaxHeight(50);
        bWing2.setMaxHeight(50);
        bWing1.setMaxHeight(50);
        bCorner1.setMaxHeight(50);
        bCorner2.setMaxHeight(50);
        bFor1.setMaxHeight(50);
        bFor2.setMaxHeight(50);


        team1Active = new boolean[MainActivity.teams.get(currentTeam1).getNumPlayers()];
        team2Active = new boolean[MainActivity.teams.get(currentTeam1).getNumPlayers()];

        for(int i=0; i < team1Active.length; i++)
        {
            team1Active[i]=false;
        }
        for(int i=0; i < team2Active.length; i++)
        {
            team2Active[i]=false;
        }

        fillTextBoxs();

        fillPlayers(MainActivity.teams.get(MainActivity.teamNames.get(currentPosition1)), 1);
        fillPlayers(MainActivity.teams.get(MainActivity.teamNames.get(currentPosition2)), 2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_socer_field, menu);
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
        if(bSwitchTeam1.isPressed())
        {
            boolean correct = true;
            do {
                correct = true;
                currentPosition1++;
                if (currentPosition1 == currentPosition2) {
                    currentPosition1++;
                    correct = false;
                }
                if (currentPosition1 >= MainActivity.numTeams) {
                    currentPosition1 = 0;
                    correct = false;
                }
            }while (!correct);
            currentTeam1 = MainActivity.teamNames.get(currentPosition1);
            fillTextBoxs();
            fillArrays();

        }
        else if(bSwitchTeam2.isPressed())
        {
            boolean correct = true;
            do {
                correct = true;
                currentPosition2++;
                if (currentPosition2 == currentPosition1) {
                    currentPosition2++;
                    correct = false;
                }
                if (currentPosition2 >= MainActivity.numTeams) {
                    currentPosition2 = 0;
                    correct = false;
                }
            }while (!correct);
            currentTeam2 = MainActivity.teamNames.get(currentPosition2);
            fillTextBoxs();
            fillArrays();
        }
        else if(bGoBack.isPressed())
        {

        }
        else if(bPlay.isPressed())
        {

        }
        else if(bKeeper1.isPressed())
        {

        }
    }

    private void fillTextBoxs()
    {
        tTeam1.setText(currentTeam1);
        tTeam2.setText(currentTeam2);
    }

    private void fillArrays()
    {
        team1.clear();
        team2.clear();

        for(int i = 0; i < MainActivity.teams.get(currentTeam1).getNumPlayers(); i++)
        {
            team1.add(MainActivity.teams.get(currentTeam1).getPlayer(i).getName());
        }
        for(int i = 0; i < MainActivity.teams.get(currentTeam2).getNumPlayers(); i++)
        {
            team2.add(MainActivity.teams.get(currentTeam2).getPlayer(i).getName());
        }

        team1Active = new boolean[MainActivity.teams.get(currentTeam1).getNumPlayers()];
        team2Active = new boolean[MainActivity.teams.get(currentTeam1).getNumPlayers()];
        for(int i=0; i < team1Active.length; i++)
        {
            team1Active[i]=false;
        }
        for(int i=0; i < team2Active.length; i++)
        {
            team2Active[i]=false;
        }
    }

    private void addButtonImage(ImageButton button, String teamToUse, String player)
    {
        if(player.equals(""))
        {
            button.setImageResource(R.drawable.blank);
        }
        else
        {
            button.setImageResource(MainActivity.teams.get(teamToUse).getPlayer(player).getPlayerPic());
        }
    }

    private void fillPlayers(SoccerTeam st, int team)
    {
        for(int i=0; (i < 5); i++)
        {
            boolean playerFound = false;
            int j = 0;
            do
            {
                if( st.getPlayer(j).getCurrentPosition() == -1)
                {
                    playerFound = true;
                }
                else
                {
                    j++;
                }
            }while (!playerFound && j < st.getNumPlayers());
            if(j < st.getNumPlayers())
            {
                if(team == 1)
                {
                    MainActivity.teams.get(currentTeam1).getPlayer(j).setActive(1);
                    team1positions[i] = st.getPlayer(j).getName();
                    st.getPlayer(j).setCurrentPosition(i);
                }
                else
                {
                    st.getPlayer(j).setActive(1);
                    team2positions[i] = st.getPlayer(j).getName();
                    st.getPlayer(j).setCurrentPosition(i);
                }
            }
            else
            {
                if(team == 1)
                {
                    team1positions[i] = "";
                }
                else
                {
                    team2positions[i] = "";
                }
            }

            if(team == 1)
            {
                switch (i) {
                    case 0:
                    {
                        addButtonImage(bKeeper1, currentTeam1, team1positions[i]);
                        break;
                    }
                    case 1:
                    {
                        addButtonImage(bSweep1, currentTeam1, team1positions[i]);
                        break;
                    }
                    case 2:
                    {
                        addButtonImage(bWing1, currentTeam1, team1positions[i]);
                        break;
                    }
                    case 3:
                    {
                        addButtonImage(bCorner1, currentTeam1, team1positions[i]);
                        break;
                    }
                    case 5:
                    {
                        addButtonImage(bFor1, currentTeam1, team1positions[i]);
                        break;
                    }
                }
            }
            else
            {
                switch (i) {
                    case 0:
                    {
                        addButtonImage(bKeeper2, currentTeam2, team2positions[i]);
                        break;
                    }
                    case 1:
                    {
                        addButtonImage(bSweep2, currentTeam2, team2positions[i]);
                        break;
                    }
                    case 2:
                    {
                        addButtonImage(bWing2, currentTeam2, team2positions[i]);
                        break;
                    }
                    case 3:
                    {
                        addButtonImage(bCorner2, currentTeam2, team2positions[i]);
                        break;
                    }
                    case 5:
                    {
                        addButtonImage(bFor2, currentTeam2, team2positions[i]);
                        break;
                    }
                }
            }

        }
    }

}
