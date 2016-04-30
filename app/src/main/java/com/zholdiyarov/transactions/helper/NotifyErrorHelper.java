package com.zholdiyarov.transactions.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * THIS CLASS PROVIDES ONLY ONE METHOD WHICH PRINTS ERROR MESSAGE IN ALERT DIALOG
 * Parameters: This class only need activity instance as a parameter.
 * <p/>
 * email: zholdiyarov@gmail.com
 */
public class NotifyErrorHelper {

    /* Variables */
    private Activity activity;

    public NotifyErrorHelper(Activity activity) {
        this.activity = activity;
    }

    public void notifyUserError(String text) {
        new AlertDialog.Builder(activity)
                .setTitle("Something wrong")
                .setMessage(text)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
