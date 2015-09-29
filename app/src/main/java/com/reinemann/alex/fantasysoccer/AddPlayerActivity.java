package com.reinemann.alex.fantasysoccer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;

public class AddPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bCreate,
                   bClose,
                   bChangePosition;

    private EditText etFirstName,
            etLastName,
            etUniform;

    private TextView tPosition;

    private int position = 0;

    String teamToUse;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        i = getIntent();
        teamToUse = i.getStringExtra("teamToGive");

        bCreate = (Button) findViewById(R.id.createPlayer);
        bClose = (Button) findViewById(R.id.goBack);
        bChangePosition = (Button) findViewById(R.id.bChangePosition);
        etFirstName = (EditText) findViewById(R.id.firstNameCreator);
        etLastName = (EditText) findViewById(R.id.lastNameCreator);
        etUniform = (EditText) findViewById(R.id.uniformNumberCreator);
        tPosition = (TextView) findViewById(R.id.playerPosition);

        bCreate.setOnClickListener(this);
        bClose.setOnClickListener(this);
        bChangePosition.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_player, menu);
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
        if(bChangePosition.isPressed())
        {
            position++;
            if(position == 11)
            {
                position = 0;
            }
            fillBox();
        }
        else if(bCreate.isPressed())
        {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            int uniform =  Integer.parseInt(etUniform.getText().toString());

            i.putExtra("New Player", new SoccerPlayer(firstName,lastName,uniform,position, 0));

            setResult(50,i);

            finish();


        }

        else if(bClose.isPressed())
        {
            setResult(51);
            finish();
        }
    }

    private void fillBox()
    {
        switch (position)
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
}
