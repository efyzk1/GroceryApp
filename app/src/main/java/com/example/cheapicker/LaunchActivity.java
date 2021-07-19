package com.example.cheapicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class LaunchActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    RecyclerView recyclerView;

    private DatabaseReference myRef;

    private ArrayList<User> userList;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.LaunchActivity);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.FirstActivity:
                        startActivity(new Intent(getApplicationContext(),FirstActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.LaunchActivity:
                        return true;

                    case R.id.ThirdActivity:
                        startActivity(new Intent(getApplicationContext(),ThirdActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ForthActivity:
                        startActivity(new Intent(getApplicationContext(),ForthActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        recyclerView=findViewById(R.id.recycleView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRef= FirebaseDatabase.getInstance().getReference();

        userList=new ArrayList<>();
        ClearAll();
        
        GetDataFromFirebase();


        EditText editText=findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

    }

    private void filter(String text){
        ArrayList<User> filteredList=new ArrayList<>();

        for (User item: userList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        recyclerAdapter.filterList(filteredList);
    }


    private void GetDataFromFirebase() {

        Query query=myRef.child("products");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user=new User();
                    user.setImg_url(snapshot.child("img_url").getValue().toString());
                    user.setMartimg_url(snapshot.child("martimg_url").getValue().toString());
                    user.setName(snapshot.child("name").getValue().toString());
                    user.setPrice(snapshot.child("price").getValue().toString());
                    user.setWeight(snapshot.child("weight").getValue().toString());
                    user.setdescription(snapshot.child("description").getValue().toString());

                    userList.add(user);
                }
                recyclerAdapter=new RecyclerAdapter(getApplicationContext(),userList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void ClearAll(){
        if(userList !=null){
            userList.clear();

            if(recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();
            }


        }
        userList=new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem=menu.findItem(R.id.search_action);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("What are you looking for?");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}