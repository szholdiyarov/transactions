package com.zholdiyarov.transactions.objects.poj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * FOR Plain Old Java Objects from JSON. Used for rates.json
 * <p/>
 * email: zholdiyarov@gmail.com
 */
public class Rate {
    @SerializedName("from")
    @Expose
    private String from;

    @SerializedName("rate")
    @Expose
    private String rate;

    @SerializedName("to")
    @Expose
    private String to;

    /**
     * @return The from
     */
    public String getFrom() {
        return from;
    }


    /**
     * @return The rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @return The to
     */
    public String getTo() {
        return to;

    }
}
