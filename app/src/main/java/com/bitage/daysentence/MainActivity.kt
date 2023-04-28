package com.bitage.daysentence

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitage.daysentence.adapter.SentenceAdapter
import com.bitage.daysentence.dao.ListSentenceDAO
import com.bitage.daysentence.databinding.ActivityDaySentenceBinding
import com.bitage.daysentence.databinding.ActivityMainBinding
import com.bitage.daysentence.rx.RxProvider
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private val binder: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binder.sentenceList.layoutManager = LinearLayoutManager(this)

        val rxProvider = RxProvider()
        binder.sendButton.setOnClickListener {
            val sentenceDAO = ListSentenceDAO()
            disposable.add(sentenceDAO.fetch("*")
                    .subscribeOn(rxProvider.getIoScheduler())
                    .observeOn(rxProvider.getMainThreadScheduler())
                    .subscribe {
                        binder.sentenceList.adapter = SentenceAdapter(it)
                    })
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}
