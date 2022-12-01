package com.bitage.daysentence

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bitage.daysentence.dao.DaySentenceDAO
import com.bitage.daysentence.databinding.ActivityDaySentenceBinding
import com.bitage.daysentence.databinding.ActivityYourSentenceBinding
import com.bitage.daysentence.rx.RxProvider
import io.reactivex.disposables.CompositeDisposable

class DaySentenceActivity : AppCompatActivity() {
    private val binder: ActivityDaySentenceBinding by lazy {
        ActivityDaySentenceBinding.inflate(layoutInflater)
    }
    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
        val rxProvider = RxProvider()
        val daySentenceDAO = DaySentenceDAO()
        disposable.add(daySentenceDAO.fetch("")
                .subscribeOn(rxProvider.getIoScheduler())
                .observeOn(rxProvider.getMainThreadScheduler())
                .subscribe ({
                    with(binder) {
                        sentenceAuthor.text = it.author
                        sentenceTitle.text = it.title
                        sentenceContent.text = it.content
                        titleBorder.visibility = View.VISIBLE
                    }
                }) {
                    Log.e("tag", "error", it)
                    Toast.makeText(this, getString(R.string.connection_problem_text), Toast.LENGTH_LONG).show()
                })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.day_sentence_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(it.itemId) {
                R.id.yourSentenceId -> startActivity(Intent(this, YourSentenceActivity::class.java))            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}
