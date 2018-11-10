package me.melijn.dumbhome.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Helpers {

    public static String getIp() {
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                return in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
