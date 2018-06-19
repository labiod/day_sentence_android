package com.bitage.daysentence

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.bitage.daysentence.adapter.SentenceAdapter
import com.bitage.daysentence.dao.ListSentenceDAO
import com.bitage.daysentence.rx.RxProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sentenceList.layoutManager = LinearLayoutManager(this)

        val rxProvider = RxProvider()
        sendButton.setOnClickListener {
            val sentenceDAO = ListSentenceDAO()
            sentenceDAO.fetch("*")
                    .subscribeOn(rxProvider.getIoScheduler())
                    .observeOn(rxProvider.getMainThreadScheduler())
                    .subscribe {
                        sentenceList.adapter = SentenceAdapter(it)
                    }
        }
    }
}
