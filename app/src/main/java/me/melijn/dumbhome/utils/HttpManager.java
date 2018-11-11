package me.melijn.dumbhome.utils;

import me.melijn.dumbhome.fragments.ConfigFragment;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpManager {

    private static final ExecutorService threadPool = Executors.newSingleThreadExecutor();

    public static void sendRequest(final String url, final int... codes) {
        StringBuilder sb = new StringBuilder();
        for (int i : codes) {
            sb.append(",").append(i);
        }
        final String codeString = sb.toString().replaceFirst(",", "");

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .post(new MultipartBody.Builder()
                                    .setType(MultipartBody.FORM)
                                    .addFormDataPart("codes", codeString)
                                    .addFormDataPart("pulseLength", String.valueOf(ConfigFragment.pulseLength))
                                    .build())
                            .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
                    new OkHttpClient().newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
