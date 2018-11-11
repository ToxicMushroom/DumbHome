package me.melijn.dumbhome.utils;

import me.melijn.dumbhome.activities.MainActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Helpers {

    private static final ExecutorService threadPool = Executors.newSingleThreadExecutor();
    public static void getIp(final String configuredIp) {
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://checkip.amazonaws.com");
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                        MainActivity.local = configuredIp.equalsIgnoreCase(in.readLine());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
