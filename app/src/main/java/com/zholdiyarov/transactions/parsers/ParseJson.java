package com.zholdiyarov.transactions.parsers;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zholdiyarov.transactions.objects.poj.Rate;
import com.zholdiyarov.transactions.objects.poj.Transaction;

import java.io.IOException;

/**
 * Created by szholdiyarov on 4/29/16.
 * READS DATA BY CALLING JsonFileReader.getJson AND PASS IT TO THE GSON LIBRARY.
 */
public class ParseJson {

    /* VARIABLE DECLARATION */
    private String file;
    private Activity activity;
    private JsonFileReader jsonFileReader;
    private Gson gson;

    /* ACTIVITY FOR GETTING ASSETS AND FILE AS A FILENAME */
    public ParseJson(Activity activity, String file) {
        this.file = file;
        this.activity = activity;
        jsonFileReader = new JsonFileReader(activity, file);
        gson = new GsonBuilder().create();
    }

    /**
     * GET TRANSACTION POJ ARRAY
     */
    public Transaction[] getTransactions() throws IOException {
        return gson.fromJson(jsonFileReader.getJson(), Transaction[].class);
    }

    /**
     * GET RATE POJ ARRAY
     */
    public Rate[] getRates() throws IOException {
        return gson.fromJson(jsonFileReader.getJson(), Rate[].class);
    }


}
