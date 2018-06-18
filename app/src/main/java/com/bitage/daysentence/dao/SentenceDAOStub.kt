package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO

class SentenceDAOStub : ISentenceDAO {
    override fun fetch(filter: String, callback: SentenceCallback) {
        val result = ArrayList<SentenceDTO>()
        result.add(SentenceDTO("f04706a2-8b21-4763-b046-1195ed5919fa",
                "Dawanie i branie",
                "Przywódca bierze mniej, niż dostaje, I daje więcej, aniżeli wziął.",
                "Ketab-e Amu Daria"))
        result.add(SentenceDTO("d9ab12dc-b2f1-4a8e-8fee-81e2711dd34a",
                "Głos po nocy",
                "Jakis głos szepnął mi zeszłej nocy: 'Nie ma nic takiego, jak głosy szepcące po nocy'.",
                "Hajdar Ansari"))
    }
}