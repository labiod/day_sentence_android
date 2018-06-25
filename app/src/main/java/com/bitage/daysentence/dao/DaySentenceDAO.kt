package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO
import com.bitage.daysentence.service.DaySentenceService
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DaySentenceDAO : ISentenceDAO<SentenceDTO> {
    val service: DaySentenceService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(DaySentenceService.API_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        service = retrofit.create(DaySentenceService::class.java)
    }
    override fun fetch(filter: String): Observable<SentenceDTO> {
        return service.getDaySentence()
    }
}