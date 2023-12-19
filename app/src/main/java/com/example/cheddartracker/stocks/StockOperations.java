package com.example.cheddartracker.stocks;

public class StockOperations {

    public static double  CalculateCurrentOperationPosition(UserStockPosition userStock){
        //Calculate the winning or losses
        Double currentOperationPosition = (userStock.getLastStockPrice() * userStock.getNumberOfStocks()) -
                ( userStock.getAcquisitionPrice() * userStock.getNumberOfStocks()) - userStock.getFees();

        //if the result is positive, make text green, if negative, make text red
        return currentOperationPosition;
    }
}
