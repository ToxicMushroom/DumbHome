package me.melijn.dumbhome.utils

import me.melijn.dumbhome.activities.MainActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.Executors

private val threadPool = Executors.newSingleThreadExecutor()
fun getIp(configuredIp: String) {
    threadPool.submit {
        try {
            val url = URL("http://checkip.amazonaws.com")
            BufferedReader(InputStreamReader(url.openStream())).use { `in` -> MainActivity.local = configuredIp.equals(`in`.readLine(), ignoreCase = true) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}