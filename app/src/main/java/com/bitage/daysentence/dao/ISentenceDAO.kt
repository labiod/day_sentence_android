package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO
import retrofit2.Call
import retrofit2.Callback

interface ISentenceDAO {
    fun fetch(filter: String, callback: SentenceCallback)
}

interface SentenceCallback : Callback<List<SentenceDTO>> {

}
