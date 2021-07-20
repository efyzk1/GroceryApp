package com.example.cheapicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FirstActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int[] images;
    String[] placeNames;
    String[] placeGuides;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        recyclerView=findViewById(R.id.recyclerView);
        images=new int[]{R.mipmap.tesco,R.mipmap.aeon,R.drawable.happyfresh,R.drawable.youbeli,R.drawable.shopeemart,R.drawable.lazada,R.drawable.pandamartlogo,R.drawable.redtick,R.drawable.mydin,R.drawable.zenxin};
        placeNames=new String[]{"Tesco/Lotus'","AEON","Happy Fresh","Youbeli","Shopee","Lazada","Pandamart","RedTick","MyDin","Zen Xin Organic"};
        placeGuides=new String[]{"https://eshop.tesco.com.my/groceries/","https://myaeon.com.my/","https://m.happyfresh.my/","https://www.youbeli.com/","https://shopee.com.my/shopee_mart","https://www.lazada.com.my/shop-groceries/","https://www.foodpanda.my/zh/contents/pandamart-grocery-delivery-malaysia"," https://shop.redtick.com/","https://mydinexpress.my/hypermart/","https://zenxin.com.my/ "};

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FirstActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter=new MyAdapter(FirstActivity.this,images,placeNames,placeGuides);
        recyclerView.setAdapter(myAdapter);




        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.FirstActivity);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.FirstActivity:
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
                        startActivity(new Intent(getApplicationContext(),ForthActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}