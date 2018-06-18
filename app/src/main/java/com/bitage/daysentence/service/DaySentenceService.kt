package com.bitage.daysentence.service

import com.bitage.daysentence.dto.SentenceDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DaySentenceService {
    companion object {
        val API_SERVER = "https://b86hfjbd3e.execute-api.eu-west-2.amazonaws.com"
    }

    @GET("sentence_dev")
    fun getAllSentences(@Query("sentenceId") sentenceId: String): Call<List<SentenceDTO>>
}