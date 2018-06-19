package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO
import io.reactivex.Observable

class DaySentenceDAO : ISentenceDAO<SentenceDTO> {
    override fun fetch(filter: String): Observable<SentenceDTO> {
        return Observable.just(SentenceDTO("f04706a2-8b21-4763-b046-1195ed5919fa",
                "Dawanie i branie",
                "Przywódca bierze mniej, niż dostaje, I daje więcej, aniżeli wziął.",
                "Ketab-e Amu Daria"))
    }
}