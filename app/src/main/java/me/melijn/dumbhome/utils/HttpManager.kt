package me.melijn.dumbhome.utils

import me.melijn.dumbhome.fragments.ConfigFragment
import okhttp3.Credentials
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.Executors
    private val threadPool = Executors.newSingleThreadExecutor()

    fun sendRequest(url: String, user: String, token: String, vararg codes: Int) {
        val sb = StringBuilder()
        for (i in codes) {
            sb.append(",").append(i)
        }
        val codeString = sb.toString().replaceFirst(",".toRegex(), "").replace("0,|,0".toRegex(), "")

        threadPool.execute {
            try {
                val request = Request.Builder()
                        .url(url)
                        .post(MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("codes", codeString)
                                .addFormDataPart("pulseLength", ConfigFragment.pulseLength.toString())
                                .build())
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .addHeader("authorization", Credentials.basic(user, token))
                        .build()
                OkHttpClient().newCall(request).execute()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }