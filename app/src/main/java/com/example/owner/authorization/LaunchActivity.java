package com.example.owner.authorization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    //initialize variables
    private Button buttonSignIn1;
    private Button buttonSignUp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch2);

        buttonSignIn1 = (Button) findViewById(R.id.buttonSignIn1);
        buttonSignUp1 = (Button) findViewById(R.id.buttonSignUp1);

        buttonSignIn1.setOnClickListener(this);
        buttonSignUp1.setOnClickListener(this);


    }

    @Override
    public void onClick (View view) {
        if(view == buttonSignIn1) {
            //closing the activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(view == buttonSignUp1) {
            //closing the activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }

    }

}
