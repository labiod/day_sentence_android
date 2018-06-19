package com.bitage.daysentence.dao

import com.bitage.daysentence.dto.SentenceDTO
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class IListSentenceDAOTest {

    lateinit var sentenceDAO: ListSentenceDAO
    lateinit var mSentenceList: List<SentenceDTO>

    init {

    }

    @Before
    fun setupTest() {
        mSentenceList = listOf(
                SentenceDTO("f04706a2-8b21-4763-b046-1195ed5919fa",
                        "Dawanie i branie",
                        "Przywódca bierze mniej, niż dostaje, I daje więcej, aniżeli wziął.",
                        "Ketab-e Amu Daria"),
                SentenceDTO("d9ab12dc-b2f1-4a8e-8fee-81e2711dd34a",
                        "Głos po nocy",
                        "Jakis głos szepnął mi zeszłej nocy: 'Nie ma nic takiego, jak głosy szepcące po nocy'.",
                        "Hajdar Ansari")
        )
        sentenceDAO = Mockito.mock(ListSentenceDAO::class.java)
        `when`(sentenceDAO.fetch("*")).thenReturn(Observable.just(mSentenceList))
    }

    @Test
    fun testSentence_checkThatFetchReturnCorrectData() {
        val resultList = sentenceDAO.fetch("*")
        resultList
                .test()
                .assertSubscribed()
                .assertResult(mSentenceList)
    }

    @Test
    fun testSentence_checkValueOfReturnData() {
        val observer: TestObserver<List<SentenceDTO>> = TestObserver()
        val result = sentenceDAO.fetch("*")

        result.subscribe(observer)
        observer.assertNoErrors()
        assertTrue(observer.values() != null)
        var containsItem = false
        for (item in observer.values()[0]) {
            if (item.id == "f04706a2-8b21-4763-b046-1195ed5919fa") {
                containsItem = true
            }
        }

        assertTrue("Error: list doesn't contains item with id: f04706a2-8b21-4763-b046-1195ed5919fa", containsItem)
    }
}