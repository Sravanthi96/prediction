package com.ai.prediction;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Regression {

    public static void main(String args[]) {
        try {
            //Load the data set
            List<String[]> csvdata = loadCSV("src/main/resources/customer_purchases.csv");
            //prepare the regression model
            SimpleRegression regression = new SimpleRegression();
            //Add data to regression
            for(int i =1; i < csvdata.size(); i++) {
                String[] row = csvdata.get(i);
                double income = Double.parseDouble(row[2]);
                double purchaseData = Double.parseDouble(row[3]);
                regression.addData(income,purchaseData);
            }

            //Print model statistcs
            System.out.println("=== Model Summary ===");
            System.out.printf("R-squared: %.4f\n", regression.getRSquare());
            System.out.printf("Intercept: %.2f\n", regression.getIntercept());
            System.out.printf("Slope: %.4f\n", regression.getSlope());
            System.out.printf("Standard Error: %.4f\n\n", regression.getRegressionSumSquares());

            //Make predictions for new customers
            predictPurchase(regression,40000);
            predictPurchase(regression,55000);
            predictPurchase(regression,85000);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String[]>  loadCSV(String filepath) throws IOException, CsvException {
        try(CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            return csvReader.readAll();
        }
    }

    private static void predictPurchase(SimpleRegression regression,double income) {
        double predictedAmount = regression.predict(income);
        System.out.printf("Predicted purchase for $%,.2f income: $%,.2f\n",
                income, predictedAmount);
    }
}
