package com.example.owner.authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    //Defining view object
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

   //defining firebase object
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing firebase object
       firebaseAuth =  FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

        }


        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        progressDialog = new ProgressDialog(this);

        //attaching Listener to button
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser() {
        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Checking if email and password are empty
        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if email and passwords are not empty
        //we will first show a progressbar

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                       //checking if task successful
                        if(task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                        }else {
                            Toast.makeText(MainActivity.this, "Could not register... Please try again", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }

                });

    }

        @Override
                public void onClick(View view) {
if(view == buttonRegister) {
    //close activity
    finish();
    //register
    registerUser();
}
            if(view == textViewSignin)
            {
                //close activity
                finish();
               //will open login activity here
                startActivity(new Intent(this, LoginActivity.class));
            }
    }
}
