package com.example.cheapicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThirdActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.ThirdActivity);
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
                        return true;

                    case R.id.ForthActivity:
                        startActivity(new Intent(getApplicationContext(),ForthActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}