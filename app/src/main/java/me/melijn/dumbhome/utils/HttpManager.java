package me.melijn.dumbhome.utils;

import java.io.IOException;
import java.util.Arrays;

import me.melijn.dumbhome.fragments.ConfigFragment;
import okhttp3.Credentials;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpManager {

    public static void sendRequest(String url, int... codes) {
        try {
            new OkHttpClient().newCall(new Request.Builder()
                    .url(url)
                    .post(new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("codes", Arrays.toString(codes))
                            .addFormDataPart("pulseLength", String.valueOf(ConfigFragment.pulseLength))
                            .build())
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", Credentials.basic("Dummy", "Data"))
                    .build()
            ).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
