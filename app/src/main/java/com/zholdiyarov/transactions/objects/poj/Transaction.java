package com.zholdiyarov.transactions.objects.poj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by szholdiyarov on 4/29/16.
 * THIS IS POJ(PLAIN OLD JAVA) CLASS FOR TRANSACTION OBJECT
 */


public class Transaction {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("currency")
    @Expose
    private String currency;

    /**
     * @return The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}