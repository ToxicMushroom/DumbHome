package me.melijn.dumbhome.utils

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets


fun getObjFromStream(stream: InputStream): JSONObject? {
    try {
        val streamReader = BufferedReader(InputStreamReader(stream, StandardCharsets.UTF_8))
        val responseStrBuilder = StringBuilder()
        streamReader.forEachLine {
            responseStrBuilder.append(it)
        }
        return JSONObject(responseStrBuilder.toString())
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return null
}