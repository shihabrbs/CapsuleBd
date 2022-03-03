package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rightbrain.officeproject.R;

public class BottomMenuActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        bottomNavigationView = findViewById(R.id.bottomnavigationviewmenu);

      //  bottomNavigationView.getMenu().getItem(2).isEnabled(false);

     /*   MenuItem menuItem = bottomNavigationView.getMenu().getItem(2);
        menuItem.setCheckable(false);*/
        Menu menu = bottomNavigationView.getMenu();
        menu.getItem(2).setEnabled(false);



    }
}