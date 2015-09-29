package com.reinemann.alex.fantasysoccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Hashtable;

public class PlayerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private static Hashtable<String, SoccerTeam> teams  = new Hashtable<>();
    private int numTeams;
    private ArrayList<String> teamNames;

    private String currentPlayer;
    private String currentTeam;
    private String currentSwitch;
    private int    currentPosition;
    private int    currentPlayerPosition;
    private int    currentSwitchingPos;
    private boolean hasChanged = false;

    private Button bIncGoals;
    private Button bIncAssists;
    private Button bIncShots;
    private Button bIncSaves;
    private Button bIncFouls;
    private Button bIncYelCards;
    private Button bIncRedCards;
    private Button bSwitchTeam;
    private Button bAddPlayer;
    private Button bPosition;
    private Button bRemovePlayer;
    private Button bGoBack;
    private Button bCycleTeams;
    private Button bCyclePlayers;
    private Button bCycleSwitchingTeam;

    private TextView tUniformNumber;
    private TextView tNumGoals;
    private TextView tNumSaves;
    private TextView tNumShots;
    private TextView tNumAssists;
    private TextView tNumFouls;
    private TextView tNumYelCards;
    private TextView tNumRedCards;
    private TextView tPosition;
    private TextView tTeamName;
    private TextView tPlayerName;
    private TextView tSwitchingTeam;

    private ImageView imPlayerPic;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view);

        intent = getIntent();

        currentTeam = intent.getStringExtra("Current");
        currentPosition = intent.getIntExtra("CurrentPos", 0);
        teamNames = intent.getStringArrayListExtra("Team Array");
        numTeams = intent.getIntExtra("NumTeams", 0);

        for(int i = 0; i < numTeams; i++)
        {
            SoccerTeam st = (SoccerTeam) intent.getSerializableExtra(teamNames.get(i));

            teams.put(teamNames.get(i),st);
        }


        if(teams.get(currentTeam).getNumPlayers() > 0)
        {
            currentPlayerPosition = 0;
            currentPlayer = teams.get(currentTeam).getPlayer(currentPlayerPosition).getName();
        }

        bIncGoals = (Button) findViewById(R.id.bIncreaseGoals);
        bIncAssists =  (Button) findViewById(R.id.bIncreaseAssists);
        bIncSaves = (Button) findViewById(R.id.bIncreaseSaves);
        bIncShots = (Button) findViewById(R.id.bIncreaseShots);
        bIncFouls = (Button) findViewById(R.id.bIncreaseFouls);
        bIncYelCards = (Button) findViewById(R.id.bIncreaseYellowCards);
        bIncRedCards = (Button) findViewById(R.id.bIncreaseRedCards);
        bSwitchTeam = (Button) findViewById(R.id.bSwitchTeam);
        bAddPlayer = (Button) findViewById(R.id.bAddPlayer);
        bPosition = (Button) findViewById(R.id.bChangePosition);
        bRemovePlayer = (Button) findViewById(R.id.bRemovePlayer);
        bGoBack = (Button) findViewById(R.id.goBack);
        bCycleTeams = (Button) findViewById(R.id.cycleTeams);
        bCyclePlayers = (Button) findViewById(R.id.cyclePlayers);
        bCycleSwitchingTeam = (Button) findViewById(R.id.cycleSwitchingTeam);

        tUniformNumber = (TextView) findViewById(R.id.uniformNumber);
        tNumGoals = (TextView) findViewById(R.id.playerGoals);
        tNumSaves = (TextView) findViewById(R.id.playerSaves);
        tNumShots = (TextView) findViewById(R.id.playerShots);
        tNumAssists = (TextView) findViewById(R.id.playerAssists);
        tNumFouls = (TextView) findViewById(R.id.playerFouls);
        tNumYelCards = (TextView) findViewById(R.id.playerYellowCards);
        tNumRedCards = (TextView) findViewById(R.id.playerRedCards);
        tPosition = (TextView) findViewById(R.id.playerPosition);
        tTeamName = (TextView) findViewById(R.id.teamName);
        tPlayerName = (TextView) findViewById(R.id.PlayerName);
        tSwitchingTeam = (TextView) findViewById(R.id.teamToChangeTo);

        imPlayerPic = (ImageView) findViewById(R.id.playerPicture);


        bIncGoals.setOnClickListener(this);
        bIncAssists.setOnClickListener(this);
        bIncShots.setOnClickListener(this);
        bIncSaves.setOnClickListener(this);
        bIncFouls.setOnClickListener(this);
        bIncYelCards.setOnClickListener(this);
        bIncRedCards.setOnClickListener(this);
        bAddPlayer.setOnClickListener(this);
        bSwitchTeam.setOnClickListener(this);
        bPosition.setOnClickListener(this);
        bRemovePlayer.setOnClickListener(this);
        bGoBack.setOnClickListener(this);
        bCycleTeams.setOnClickListener(this);
        bCyclePlayers.setOnClickListener(this);
        bCycleSwitchingTeam.setOnClickListener(this);

        currentSwitchingPos = currentPosition;
        currentSwitch = currentTeam;

        fillTextFields();

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
        else if(bCycleTeams.isPressed())
        {
            currentPosition++;
            if(currentPosition >= numTeams)
            {
                currentPosition = 0;
            }
            currentTeam = teamNames.get(currentPosition);

            currentPlayerPosition = 0;
            if(teams.get(currentTeam).getNumPlayers() != 0)
            {
                currentPlayer = teams.get(currentTeam).getPlayer(currentPlayerPosition).getName();
            }
            else
            {
                currentPlayer = null;
            }
        }
        else if(bCyclePlayers.isPressed())
        {
            if(teams.get(currentTeam).getNumPlayers() != 0) {
                currentPlayerPosition++;
                if (currentPlayerPosition >= teams.get(currentTeam).getNumPlayers()) {
                    currentPlayerPosition = 0;
                }
                currentPlayer = teams.get(currentTeam).getPlayer(currentPlayerPosition).getName();
            }

        }
        else if (bCycleSwitchingTeam.isPressed())
        {
            currentSwitchingPos++;
            if(currentSwitchingPos >= numTeams)
            {
                currentSwitchingPos = 0;
            }
            currentSwitch = teamNames.get(currentSwitchingPos);
            tSwitchingTeam.setText(currentSwitch);
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
                pos = 0;
            }
            teams.get(currentTeam).players.get(currentPlayer).setPositionNum(pos);
    }
        else if(bSwitchTeam.isPressed())
        {
            SoccerPlayer sp = teams.get(currentTeam).getPlayer(currentPlayer);
            teams.get(currentSwitch).addPlayer(sp);
            teams.get(currentTeam).removePlayer(currentPlayer);
            currentTeam = currentSwitch;
            currentPosition = currentSwitchingPos;
            tSwitchingTeam.setText("");
        }
        else if(bAddPlayer.isPressed())
        {
            Intent i = new Intent(PlayerViewActivity.this,AddPlayerActivity.class);

            startActivityForResult(i, 100);
        }
        else if(bRemovePlayer.isPressed())
        {
            if(teams.get(currentTeam).getNumPlayers() != 0) {
                String tempPlayer = currentPlayer;
                if ((currentPlayerPosition+1 < (teams.get(currentTeam).getNumPlayers() - 1))) {
                    currentPlayerPosition++;
                    String holder = teams.get(currentTeam).getPlayer(currentPlayerPosition).getName();
                    currentPlayer = holder;
                }
                else
                {
                    currentPlayerPosition = 0;
                    String holder  = teams.get(currentTeam).getPlayer(currentPlayerPosition).getName();
                    currentPlayer = holder;
                }

                if(teams.get(currentTeam).removePlayer(tempPlayer))
                {
                    Toast.makeText(getApplicationContext(),"Player successfully removed", Toast.LENGTH_LONG);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Player failed to remove", Toast.LENGTH_LONG);
                }

            }
        }
        else if(bGoBack.isPressed())
        {
            if(!hasChanged)
            {
                setResult(111);
            }
            else
            {
                for(int i = 0;i < numTeams; i++)
                {
                    intent.putExtra(teamNames.get(i),teams.get(teamNames.get(i)));
                }
                setResult(112);
            }
            finish();
        }

        fillTextFields();
    }


    public void fillTextFields()
    {
        tTeamName.setText(currentTeam);
        if (teams.get(currentTeam).getNumPlayers() > 0) {
            String name = teams.get(currentTeam).players.get(currentPlayer).getFirstName() + " " + teams.get(currentTeam).players.get(currentPlayer).getLastName();
            tPlayerName.setText(name);
            int temp = teams.get(currentTeam).players.get(currentPlayer).getUniform();
            tUniformNumber.setText("" + temp);
            temp = teams.get(currentTeam).players.get(currentPlayer).getGoals();
            tNumGoals.setText("" + temp);
            temp = teams.get(currentTeam).players.get(currentPlayer).getAssists();
            tNumAssists.setText("" + temp);
            temp = teams.get(currentTeam).players.get(currentPlayer).getShots();
            tNumShots.setText("" + temp);
            temp = teams.get(currentTeam).players.get(currentPlayer).getSaves();
            tNumSaves.setText("" + temp);
            temp = teams.get(currentTeam).players.get(currentPlayer).getFouls();
            tNumFouls.setText("" + temp);
            temp = teams.get(currentTeam).players.get(currentPlayer).getYellowCards();
            tNumYelCards.setText("" + temp);
            temp = teams.get(currentTeam).players.get(currentPlayer).getRedCards();

            if(teams.get(currentTeam).players.get(currentPlayer).getPlayerPic() != 0)
            {
                imPlayerPic.setImageResource(teams.get(currentTeam).players.get(currentPlayer).getPlayerPic());
            }
            else
            {
                imPlayerPic.setImageResource(R.drawable.silhouette);
            }

            switch (teams.get(currentTeam).getPlayer(currentPlayer).getPositionNum())
            {
                case (0):
                {
                    tPosition.setText("GoalKeeper");
                    break;
                }
                case(1):
                {
                    tPosition.setText("Sweeper");
                    break;
                }
                case(2):{}
                case(3):
                {
                    tPosition.setText("Center-Back");
                    break;
                }
                case(4):{}
                case(5):
                {
                    tPosition.setText("Wing-back");
                    break;
                }
                case(6):{}
                case(7):{}
                case(8):
                {
                    tPosition.setText("Centre Midfield");
                    break;
                }
                case(9):{}
                case(10):
                {
                    tPosition.setText("Centre Forward");
                    break;
                }
            }
        }
        else
        {
            tPlayerName.setText("No Player");
            tUniformNumber.setText("Uniform ");
            tNumGoals.setText("");
            tNumAssists.setText("");
            tNumShots.setText("");
            tNumSaves.setText("");
            tNumFouls.setText("");
            tNumYelCards.setText("");
            tNumRedCards.setText("");
            tPosition.setText("");
            imPlayerPic.setImageResource(R.drawable.silhouette);


        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 100)
            if (resultCode == 50) {
                SoccerPlayer sp = (SoccerPlayer) data.getSerializableExtra("New Player");

                if (teams.get(currentTeam).addPlayer(sp))
                {
                    currentPlayer = sp.getName();
                    currentPlayerPosition = teams.get(currentTeam).getPlayerPosition(sp);
                    hasChanged = true;
                    Toast.makeText(getApplicationContext(), "Player successfully added", Toast.LENGTH_LONG);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Player not added", Toast.LENGTH_LONG);
                }
            }
    }

}
