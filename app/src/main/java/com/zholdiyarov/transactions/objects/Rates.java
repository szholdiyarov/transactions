package com.zholdiyarov.transactions.objects;

import com.zholdiyarov.transactions.objects.poj.Rate;
import com.zholdiyarov.transactions.objects.poj.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szholdiyarov on 4/30/16.
 * THIS STATIC CLASS DESIGNED TO STORE ALL RATES.
 * TREE IS USED FOR CONVERTING CURRENCY AND MAPS FOR SIMPLE DISPLAY OPERATIONS
 */
public class Rates {

    /* VARIABLE DECLARATION */
    private static Rate[] ratesArr;
    private static final Rates rates = new Rates();
    private static HashMap<String, ArrayList<Rate>> ratesMap = new HashMap<>();
    private String baseCurrency = "GBP";

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
    }

    /* TAKES A TRANSACTION LIST FOR A SPECIFIED SKU AND RETURNS TOTAL AMOUNT OF TRANSACTIONS IN GBP */
    public double getTotalFor(ArrayList<Transaction> transactions) {
        double result = 0;
        for (int i = 0; i < transactions.size(); i++) {

            String convertFromCurrency = transactions.get(i).getCurrency();
            double amountToConvert = Double.parseDouble(transactions.get(i).getAmount());
            result += convert(convertFromCurrency, amountToConvert, "");

        }
        return result;
    }


    /**
     * HASHMAP STORES A SKU AS THE FIRST ARGUMENT, AND LIST OF RATES AS A VALUE.
     * THIS LIST REPRESENTS CURRENCIES TO WHICH THIS KEY SKU CAN BE CONVERTED TO
     * FOR EXAMPLE, (USD,<EUR,GBP>);
     *
     * @param ratesArrC
     */
    private static void initializeMap(Rate[] ratesArrC) {
        for (int i = 0; i < ratesArrC.length; i++) {

            Rate tempRate = ratesArrC[i]; // current rate object from the array of rates
            ArrayList<Rate> list; // list stores currencies to which sku can be converted

            if (ratesMap.get(tempRate.getFrom()) == null) { // if hash map does not contain current rate
                list = new ArrayList<>(); // create new list
            } else { // if there is already a rate list, then get it and update it
                list = ratesMap.get(tempRate.getFrom());
            }
            list.add(tempRate);
            ratesMap.put(tempRate.getFrom(), list);
        }
    }


    /**
     * RECURSIVE METHOD WHICH TAKES :
     *
     * @param from = from which currency to gbp?
     * @param rate = how much to convert?
     * @param to   = initially empty
     * @return
     */
    public double convert(String from, double rate, String to) {

        if (from.equalsIgnoreCase(baseCurrency)) { // if need convert GBP to GBP = > return rate
            return rate;
        } else if (to.equalsIgnoreCase(baseCurrency)) { // this is needed to stop recursion, so we get final rate
            return rate;
        } else {

            ArrayList<Rate> temp = ratesMap.get(from); // get currencies to which requested currency can be converted
            for (int i = 0; i < temp.size(); i++) { // iterate
                Double skuRate = Double.parseDouble(temp.get(i).getRate()); // get current rate between "from" and "to"

                /* update rate */
                if (rate == 0.0) {
                    rate = skuRate;
                } else {
                    rate *= skuRate;
                }

                /* There is direct conversion rate, so stop here and return calculated rate. */
                if (temp.get(i).getTo().equalsIgnoreCase(baseCurrency)) {
                    return convert(from, rate, baseCurrency);
                } else { // recursive iteration with update rate
                    return convert(temp.get(i).getTo(), rate, "");
                }
            }
        }
        return 0.0;

    }
}
