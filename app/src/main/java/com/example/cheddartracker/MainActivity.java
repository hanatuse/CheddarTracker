package com.example.cheddartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cheddartracker.auxelements.MyApplication;
import com.example.cheddartracker.external.FileManager;
import com.example.cheddartracker.stocks.UserStockPosition;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button checkPortfolio = findViewById(R.id.checkPortfolio);
        Button options = findViewById(R.id.options);

        FileManager fileManager = new FileManager(getApplicationContext());
//        ArrayList<UserStockPosition> listUserStockPosition = new ArrayList<UserStockPosition>();
//        listUserStockPosition=fileManager.readUserPositionsFromFile();
//        MyApplication.getApplication().setAllUserStockPosition(listUserStockPosition);

        checkPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                startActivity(new Intent(MainActivity.this, ListOfStocks.class));
            }
        });
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                startActivity(new Intent(MainActivity.this, AddPosition.class));
            }
        });
    }
}