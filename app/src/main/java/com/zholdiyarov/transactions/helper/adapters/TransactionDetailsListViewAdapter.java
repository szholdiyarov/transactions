package com.zholdiyarov.transactions.helper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zholdiyarov.transactions.R;
import com.zholdiyarov.transactions.objects.Rates;
import com.zholdiyarov.transactions.objects.poj.Rate;
import com.zholdiyarov.transactions.objects.poj.Transaction;

import java.util.ArrayList;

/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * THIS IS LIST VIEW ADAPTER TO DISPLAY DETAILS OF TRANSACTIONS, CONVERT CURRENCIES TO GBP
 * <p/>
 * email: zholdiyarov@gmail.com
 */
public class TransactionDetailsListViewAdapter extends BaseAdapter {

    private ArrayList<Transaction> transactions;
    private static LayoutInflater inflater = null;

    public TransactionDetailsListViewAdapter(Context context, ArrayList transactions) {
        this.transactions = transactions;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return transactions.size();
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

        /* DISPLAYS ORIGINAL CURRENCY AND AMOUNT */
        holder.textView_title.setText(transactions.get(position).getAmount() + " " + transactions.get(position).getCurrency());

        double result = Rates.getInstance().convert(transactions.get(position).getCurrency(), Double.parseDouble(transactions.get(position).getAmount()), "");

        /* DISPLAYS CONVERTED AMOUNT */
        holder.textView_body.setText(String.format("%.1f", result)+ " GBP");
        return rowView;
    }

}