package com.example.cheapicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        if(auth.getCurrentUser() !=null){
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this,"please wait, you are already logged in",Toast.LENGTH_LONG).show();
        }

    }
    public void login(View view){
        startActivity(new Intent(MainActivity.this,LaunchActivity.class));
    }

    public  void registration(View view){

        startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
    }
}


