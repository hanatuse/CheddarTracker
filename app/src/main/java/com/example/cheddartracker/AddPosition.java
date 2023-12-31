package com.example.cheddartracker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cheddartracker.auxelements.MyApplication;
import com.example.cheddartracker.external.FileManager;
import com.example.cheddartracker.stocks.UserStockPosition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPosition extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Date purchaseDate;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_position);
        initDatePicker();
        dateButton = findViewById(R.id.stocks_purchase_date_button);
        dateButton.setText("Purchase date");

    }

    public void showDatePickerDialog(View view) {

        datePickerDialog.show();

    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

                try {
                    purchaseDate = curFormater.parse(day+"/"+month+"/"+year);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }


    public void addPosition(View view) {
        EditText stockSymbol = findViewById(R.id.stock_symbol);
        EditText numberStocks = findViewById(R.id.number_stocks);
        EditText stockPurchasePrice = findViewById(R.id.stock_purchase_price);
        EditText stockSellPrice = findViewById(R.id.stock_sell_price);
        EditText fees = findViewById(R.id.fees);

        UserStockPosition userStockPosition = new UserStockPosition();
        userStockPosition.setStockSymbol(stockSymbol.getText().toString());
        //Stock Name = Symbol only during the creation of the position
        userStockPosition.setStockName(stockSymbol.getText().toString());

        int auxNumberStocks = Integer.parseInt(numberStocks.getText().toString());
        userStockPosition.setNumberOfStocks(auxNumberStocks);


        double auxStockPurchasePrice = Double.parseDouble(stockPurchasePrice.getText().toString());
        userStockPosition.setAcquisitionPrice(auxStockPurchasePrice);

        double auxStockSellPrice = Double.parseDouble(stockSellPrice.getText().toString());
        userStockPosition.setLastStockPrice(auxStockSellPrice);

        double auxFees = Double.parseDouble(fees.getText().toString());
        userStockPosition.setFees(auxFees);

        if(purchaseDate!=null){
            userStockPosition.setAcquisitionDate(purchaseDate);
            userStockPosition.setLastUpdatedDate(purchaseDate);
        }

        //Add the new position to the global List of position
        MyApplication.getApplication().AddPositionToList(userStockPosition);

        FileManager fileManager = new FileManager(this.getApplicationContext());

        fileManager.writeAllUserPositionToFile(MyApplication.getApplication().getAllUserStockPosition());
        startActivity(new Intent(AddPosition.this, ListOfStocks.class));
    }
}

