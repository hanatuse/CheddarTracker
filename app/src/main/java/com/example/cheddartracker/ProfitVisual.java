package com.example.cheddartracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cheddartracker.PositionDetails;
import com.example.cheddartracker.auxelements.AuxClass;
import com.example.cheddartracker.auxelements.MyApplication;
import com.example.cheddartracker.stocks.Stock;
import com.example.cheddartracker.stocks.StockOperations;
import com.example.cheddartracker.stocks.UserStockPosition;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProfitVisual extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);
        Button returnButton = findViewById(R.id.return_button);
        TextView stockTotal = findViewById(R.id.stock_total);
        double totalStockGains = 0;


        ArrayList<UserStockPosition> userStockPositions = new ArrayList<>();
        TextView emptyList = findViewById(R.id.empty_list);
        if (!MyApplication.getApplication().getAllUserStockPosition().isEmpty()) {
            emptyList.setVisibility(View.INVISIBLE);
            userStockPositions = MyApplication.getApplication().getAllUserStockPosition();
        } else {
            emptyList.setVisibility(View.VISIBLE);
        }

        //Iterate all entries in csv, add up stock gains
        for (UserStockPosition userStockPosition: userStockPositions) {

            if(userStockPosition.getStockName() != null)
            {
                double auxStockGains = StockOperations.CalculateCurrentOperationPosition(userStockPosition);
                totalStockGains += auxStockGains;
//                userStockPosition.setLastStockPrice(stock.data.currentPrice);
//                userStockPosition.setStockName(stock.data.shortName);
//                //TODO set the date as well
//                Date lastUpdatedDate = Calendar.getInstance().getTime();
//                userStockPosition.setLastUpdatedDate(lastUpdatedDate);
//                userStockPositionList.set(i,userStockPosition);
//                i++;
            }
        }

        if(totalStockGains ==0){
            stockTotal.setTextColor(AuxClass.COLOR_YELLOW);
        }
        else if (totalStockGains > 0){
            stockTotal.setTextColor(AuxClass.COLOR_GREEN);
        }
        else{
            stockTotal.setTextColor(AuxClass.COLOR_RED);
        }

        //Round to just 2 decimals
        BigDecimal stockGainsBD = new BigDecimal(totalStockGains).setScale(2, RoundingMode.HALF_UP);
        double formattedStockGains = stockGainsBD.doubleValue();

        //Display stock gains in the front end
        stockTotal.setText("$ " + String.valueOf(formattedStockGains));

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return to List of stocks
                startActivity(new Intent(ProfitVisual.this, ListOfStocks.class));

            }
        });

    }
}
