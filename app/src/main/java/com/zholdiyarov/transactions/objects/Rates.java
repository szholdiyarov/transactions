package com.zholdiyarov.transactions.objects;

import com.zholdiyarov.transactions.helper.Converter;
import com.zholdiyarov.transactions.helper.tree.Edge;
import com.zholdiyarov.transactions.helper.tree.Tree;
import com.zholdiyarov.transactions.objects.poj.Rate;
import com.zholdiyarov.transactions.objects.poj.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szholdiyarov on 4/30/16.
 * THIS STATIC CLASS DESIGNED TO STORE ALL RATES IN TREE AND MAPS.
 * TREE IS USED FOR CONVERTING CURRENCY AND MAPS FOR SIMPLE DISPLAY OPERATIONS
 */
public class Rates {

    /* VARIABLE DECLARATION */
    private static Map<String, Double> rateMaps = new HashMap<>();
    private static Rate[] ratesArr;
    private static final Rates rates = new Rates();

    public static Rates getInstance() {
        return rates;
    }

    /* RETURNS ORIGINAL RATES(Rate.class) ARRAY */
    public Rate[] getRatesArr() {
        return ratesArr;
    }

    /* TAKES AN ARRAY AS ARGUMENT AND INITIALIZES MAPS AND TREE. */
    public static void setRates(Rate[] ratesArr) {
        initializeMap(ratesArr);
        initializeTree();
    }

    /* TAKESN A TRANSACTION LIST FOR A SPECIFIED SKU AND RETURNS TOTAL AMOUNT OF TRANSACTIONS IN GBP */
    public double getTotalFor(ArrayList<Transaction> transactions) {
        double result = 0;
        Converter converter;
        for (int i = 0; i < transactions.size(); i++) {

            String convertFromCurrency = transactions.get(i).getCurrency();
            double amountToConvert = Double.parseDouble(transactions.get(i).getAmount());

            converter = new Converter(convertFromCurrency, amountToConvert);
            result += converter.convert();

        }
        return result;
    }

    private static void initializeMap(Rate[] ratesArrC) {
        ratesArr = ratesArrC;
        for (int i = 0; i < ratesArr.length; i++) {
            Rate currentRate = ratesArr[i];
            rateMaps.put(currentRate.getFrom(), Double.parseDouble(currentRate.getRate()));
        }
    }

    private static void initializeTree() {
        Tree tree = Tree.getInstance();
        for (int i = 0; i < Rates.getInstance().getRatesArr().length; i++) {
            Rate currentRateItem = Rates.getInstance().getRatesArr()[i];
            Edge edge = new Edge(currentRateItem.getFrom(), currentRateItem.getTo(), Double.parseDouble(currentRateItem.getRate()));
            tree.addToTree(currentRateItem.getTo(), edge);
        }
    }
}
