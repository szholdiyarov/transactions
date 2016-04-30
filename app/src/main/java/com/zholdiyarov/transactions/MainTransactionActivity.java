package com.zholdiyarov.transactions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zholdiyarov.transactions.helper.NotifyErrorHelper;
import com.zholdiyarov.transactions.helper.adapters.TransactionsListViewAdapter;
import com.zholdiyarov.transactions.objects.Rates;
import com.zholdiyarov.transactions.objects.Transactions;
import com.zholdiyarov.transactions.parsers.ParseJson;

import java.io.IOException;

/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * THIS ACTIVITY IS A LAUNCHER ACTIVITY.
 * IT SHOWS TRASACTIONS LIST, READS DATA FROM transactions.json AND rates.json AND SAVES THEM IN THE APPROPRIATE DATA CLASSES.
 * <p/>
 * <p/>
 * email: zholdiyarov@gmail.com
 */

public class MainTransactionActivity extends AppCompatActivity {

    private ListView transactionsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transactionsListView = (ListView) findViewById(R.id.listView);

        try {
            /* FIRST, GET TRANSACTIONS */
            Transactions.getInstance().setTransactions(new ParseJson(this, "transactions.json").getTransactions());

            /* GET CURRENCY RATES, INITIALIZE MAPS AND TREE */
            Rates.getInstance().setRates(new ParseJson(this, "rates.json").getRates());

            transactionsListView.setAdapter(new TransactionsListViewAdapter(this, Transactions.getInstance().getMapOfTransactions()));

        } catch (IOException e) {
            new NotifyErrorHelper(this).notifyUserError("Error loading data(not found).");
            e.printStackTrace();
        } catch (NullPointerException e) {
            new NotifyErrorHelper(this).notifyUserError("Error reading data.");
            e.printStackTrace();
        }
    }


}
