package com.example.owner.authorization;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class SelectImageActivity extends AppCompatActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    private Button mSelectImage;
    private Button buttonExit;

    private StorageReference mStorage;

    private static final int GALLERY_INTENT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        mStorage = FirebaseStorage.getInstance().getReference();

        mSelectImage = (Button) findViewById(R.id.buttonSelect);
        buttonExit = (Button) findViewById(R.id.buttonExit);

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(view == buttonExit){
                //logging out the user
                firebaseAuth.signOut();
                //exit the application
                finish();
            }
            }
        });
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //give user option to select photo
                Intent intent = new Intent(Intent.ACTION_PICK);
                //specify type of data that can be selected
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(SelectImageActivity.this, "Upload Done.", Toast.LENGTH_LONG).show();

                }
            });
        }

    }
}
