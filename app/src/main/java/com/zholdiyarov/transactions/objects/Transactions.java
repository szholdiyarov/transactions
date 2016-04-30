package com.zholdiyarov.transactions.objects;

import com.zholdiyarov.transactions.objects.poj.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szholdiyarov on 4/30/16.
 * THIS STATIC CLASS DESIGNED TO STORE ALL TRANSACTIONS IN MAP AND ARRAY.
 * TREE IS USED FOR CONVERTING CURRENCY AND MAPS FOR SIMPLE DISPLAY OPERATIONS
 */
public class Transactions {

    /* VARIABLE DECLARATION */
    private static Transaction[] transaction;
    private static Map<String, ArrayList<Transaction>> mapOfTransactions;
    private static final Transactions transactions = new Transactions();

    public static Transactions getInstance() {
        return transactions;
    }

    public static void setTransactions(Transaction[] transactionsC) {
        transaction = transactionsC;
        initializeMap();
    }

    private static void initializeMap() {
        mapOfTransactions = new HashMap<>();
        for (int i = 0; i < transaction.length; i++) {
            Transaction currentTransaction = transaction[i];
            if (mapOfTransactions.get(currentTransaction.getSku()) == null) { // IF MAP DO NOT HAVE TRANSACTIONS FOR THIS SKU => ADD IT
                ArrayList<Transaction> trans = new ArrayList<>();
                trans.add(currentTransaction);
                mapOfTransactions.put(currentTransaction.getSku(), trans);
            } else { // TRANSACTION LIST ALREADY HAS THEM FOR THIS SKU => UPDATE THEM
                mapOfTransactions.get(currentTransaction.getSku()).add(currentTransaction);
            }
        }
    }


    public Map getMapOfTransactions() {
        return mapOfTransactions;
    }

    public ArrayList<Transaction> getListOfTransactionsFor(String sku) {
        return mapOfTransactions.get(sku);
    }

}
