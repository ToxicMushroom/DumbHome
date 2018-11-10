package me.melijn.dumbhome.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JSONConverter {

    private InputStream is;
    public JSONConverter(InputStream is){
        this.is = is;
    }

    public JSONObject getObj() {
        //Create input stream
        InputStream inputStreamObject = is;

        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, StandardCharsets.UTF_8));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            //returns the json object
            return new JSONObject(responseStrBuilder.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        //if something went wrong, return null
        return null;
    }
}