package com.zholdiyarov.transactions.helper.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zholdiyarov.transactions.R;
import com.zholdiyarov.transactions.TransactionDetailsActivity;
import com.zholdiyarov.transactions.objects.poj.Transaction;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * THIS IS LIST VIEW ADAPTER TO DISPLAY TRANSACTIONS LIST.
 * <p/>
 * email: zholdiyarov@gmail.com
 */
public class TransactionsListViewAdapter extends BaseAdapter {
    private Map<String, ArrayList<Transaction>> transactionsMap;
    private ArrayList<String> sku = new ArrayList<>();
    private Context context;

    private static LayoutInflater inflater = null;

    public TransactionsListViewAdapter(Context context, Map transactionsMap) {
        this.transactionsMap = transactionsMap;
        this.context = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initializeSkuList();
    }

    private void initializeSkuList() {
        for (Map.Entry<String, ArrayList<Transaction>> entry : transactionsMap.entrySet()) {
            sku.add(entry.getKey());
        }
    }


    @Override
    public int getCount() {
        return transactionsMap.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView textView_title, textView_body;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.list_item, null);

        holder.textView_title = (TextView) rowView.findViewById(R.id.textView_list_item_title);
        holder.textView_body = (TextView) rowView.findViewById(R.id.textView_list_item_body);

        holder.textView_title.setText("Product : " + sku.get(position));
        holder.textView_body.setText("Count : " + transactionsMap.get(sku.get(position)).size() + "");

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // IF PRESSED
                Intent transactionDetailsIntent = new Intent(context, TransactionDetailsActivity.class);
                transactionDetailsIntent.putExtra("sku", sku.get(position)); // SEND SKU VALUE AS AN INTENT EXTRA
                context.startActivity(transactionDetailsIntent); // DISPLAY DETAILS
            }
        });
        return rowView;
    }

}