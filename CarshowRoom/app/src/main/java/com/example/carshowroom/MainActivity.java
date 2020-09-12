package com.example.carshowroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.carshowroom.Fragment.HomeFragment;
import com.example.carshowroom.Fragment.SeeBooking;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectFragment = new HomeFragment();
                    break;
                case R.id.nav_cars:
                    startActivity(new Intent(MainActivity.this,PostCars.class));
                    break;
                case R.id.nav_booking:
                    selectFragment = new SeeBooking();
                    break;
            }
            if(selectFragment!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();
            }
            return true;
        }
    };




}
