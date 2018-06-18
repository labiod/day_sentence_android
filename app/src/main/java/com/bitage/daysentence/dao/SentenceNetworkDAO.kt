package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO
import com.bitage.daysentence.service.DaySentenceService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL

class SentenceNetworkDAO : ISentenceDAO {
    override fun fetch(filter: String, callback: SentenceCallback) {
        val sb = StringBuilder()

        val url = URL(DaySentenceService.API_SERVER + "/sentence_dev?sentenceId=$filter")
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val inupt = BufferedInputStream(urlConnection.inputStream)
            val bin = BufferedReader(InputStreamReader(inupt))
            var inputLine: String = bin.readLine()

            while (inputLine != null) {
                sb.append(inputLine)
                inputLine = bin.readLine()
            }
        } finally {
            urlConnection.disconnect()
        }
        val gson = GsonBuilder().create()
        val type = TypeToken.getArray(SentenceDTO::class.java).type
        val resultList: List<SentenceDTO> = gson.fromJson(sb.toString(), type)
    }
}