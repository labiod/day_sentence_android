package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback

interface ISentenceDAO<T> {
    fun fetch(filter: String) : Observable<T>
}