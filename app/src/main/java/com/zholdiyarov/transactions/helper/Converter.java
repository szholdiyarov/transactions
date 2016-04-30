package com.zholdiyarov.transactions.helper;

import com.zholdiyarov.transactions.helper.tree.Tree;

/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * THIS CLASS CONVERTS AN AMOUNT OF ONE CURRENCY TO GBP.
 * IT USES TREE TO FIND LINKS BETWEEN CURRENCY RATES IF THERE ARE NO RATES FOR A GIVEN CURRENCY.
 * <p/>
 * email: zholdiyarov@gmail.com
 */
public class Converter {

    /* VARIABLE DECLARATION */
    private String from;
    private double amount;

    /* FROM = FROM CURRENCY TO GBP */
    public Converter(String from, double amount) {
        this.from = from;
        this.amount = amount;
    }

    public double convert() {
        return Tree.getInstance().getRates(from) * amount;
    }


}
