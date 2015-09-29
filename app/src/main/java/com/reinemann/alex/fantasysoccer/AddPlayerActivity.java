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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;

public class AddPlayerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button bCreate,
            bClose;

    private EditText etFirstName,
            etLastName,
            etUniform;

    private Spinner spinPosition;

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
        etFirstName = (EditText) findViewById(R.id.firstNameCreator);
        etLastName = (EditText) findViewById(R.id.lastNameCreator);
        etUniform = (EditText) findViewById(R.id.uniformNumberCreator);
        spinPosition = (Spinner) findViewById(R.id.spinPositionChooser);

        bCreate.setOnClickListener(this);
        bClose.setOnClickListener(this);
        spinPosition.setOnItemSelectedListener(this);
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
        if(bCreate.isPressed())
        {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            int uniform =  Integer.parseInt(etUniform.getText().toString());
            int position = 1;

            i.putExtra("New Player", new SoccerPlayer(firstName,lastName,uniform,position));

            setResult(50,i);

            finish();


        }

        else if(bClose.isPressed())
        {
            setResult(51);
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
