package com.zholdiyarov.transactions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.zholdiyarov.transactions.helper.adapters.TransactionDetailsListViewAdapter;
import com.zholdiyarov.transactions.objects.Rates;
import com.zholdiyarov.transactions.objects.Transactions;
import com.zholdiyarov.transactions.objects.poj.Transaction;

import java.util.ArrayList;

public class TransactionDetailsActivity extends AppCompatActivity {

    private ListView listView_transDetails;
    private TextView textView_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        listView_transDetails = (ListView) findViewById(R.id.listView_transaction_details);
        textView_total = (TextView) findViewById(R.id.textView_total);

        String sku = getIntent().getStringExtra("sku");
        setTitle("Transactions for " + sku);

        ArrayList<Transaction> transactionsForChosenSku = Transactions.getInstance().getListOfTransactionsFor(sku);

        listView_transDetails.setAdapter(new TransactionDetailsListViewAdapter(this, transactionsForChosenSku));

        textView_total.setText("Total : " + String.format("%.1f", Rates.getInstance().getTotalFor(transactionsForChosenSku)) + "GBP");
    }
}
