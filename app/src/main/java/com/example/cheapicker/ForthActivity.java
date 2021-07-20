package com.example.cheapicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForthActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);

        logout=(Button) findViewById(R.id.signOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForthActivity.this,MainActivity.class));
            }
        });

        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.ForthActivity);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.FirstActivity:
                        startActivity(new Intent(getApplicationContext(),FirstActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.LaunchActivity:
                        startActivity(new Intent(getApplicationContext(),LaunchActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ThirdActivity:
                        startActivity(new Intent(getApplicationContext(),ThirdActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ForthActivity:
                        return true;
                }

                return false;
            }
        });

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        final TextView greetingTextView=(TextView) findViewById(R.id.Greeting);
        final TextView fullNameTextView=(TextView) findViewById(R.id.fullName);
        final TextView emailTextView=(TextView) findViewById(R.id.emailAddress);
        final TextView passwordTextView=(TextView) findViewById(R.id.password);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userProfile=snapshot.getValue(UserModel.class);
                if(userProfile!=null){
                    String name=userProfile.name;
                    String email=userProfile.email;
                    String password=userProfile.password;

                    greetingTextView.setText("Welcome, "+name+"!");
                    fullNameTextView.setText(" "+name);
                    emailTextView.setText(" "+email);
                    passwordTextView.setText(" "+password);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ForthActivity.this,"Something wrong happened!",Toast.LENGTH_LONG).show();

            }
        });


    }
}