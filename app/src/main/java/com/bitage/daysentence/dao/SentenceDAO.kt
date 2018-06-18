package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO
import com.bitage.daysentence.service.DaySentenceService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SentenceDAO : ISentenceDAO {
    val service: DaySentenceService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(DaySentenceService.API_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(DaySentenceService::class.java)
    }
    override fun fetch(filter: String, callback: SentenceCallback) {
        service.getAllSentences(filter).enqueue(callback)
    }
}