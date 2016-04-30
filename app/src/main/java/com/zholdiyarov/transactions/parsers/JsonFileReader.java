package com.zholdiyarov.transactions.parsers;

import android.app.Activity;

import com.zholdiyarov.transactions.helper.NotifyErrorHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by szholdiyarov on 4/29/16.
 * THIS CLASS READS DATA FROM THE GIVEN JSON FILE AND RETURNS IT AS A STRING;
 */
public class JsonFileReader {

    /* VARIABLE DECLARATION */
    private String fileName;
    private Activity activity;

    /* ACTIVITY FOR GETTING ASSETS AND FILENAME TO GET A FILE */
    protected JsonFileReader(Activity activity, String fileName) {
        this.fileName = fileName;
        this.activity = activity;
    }

    /* READS ASSETS AS AN INPUT STREAM AND RETURN BYTE ARRAY */
    private byte[] readJsonFromAssetsBytes() {
        try {
            InputStream inputStream = activity.getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            return buffer;
        } catch (IOException e) {
            new NotifyErrorHelper(activity).notifyUserError("Error with reading a JSON file from assets. Try again.");
            e.printStackTrace();
        }
        return null; // SOMETHING WRONG
    }

    /* RETURNS READ FILE AS A STRING SO IT CAN BE PASSED TO GSON */
    protected String getJson() throws IOException {
        return new String(readJsonFromAssetsBytes(), "UTF-8");
    }


}
