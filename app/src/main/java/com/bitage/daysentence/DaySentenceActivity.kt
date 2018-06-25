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
import com.bitage.daysentence.rx.RxProvider
import kotlinx.android.synthetic.main.activity_day_sentence.*

class DaySentenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_sentence)
        val rxProvider = RxProvider()
        val daySentenceDAO = DaySentenceDAO()
        daySentenceDAO.fetch("")
                .subscribeOn(rxProvider.getIoScheduler())
                .observeOn(rxProvider.getMainThreadScheduler())
                .subscribe ({
                    sentenceAuthor.text = it.author
                    sentenceTitle.text = it.title
                    sentenceContent.text = it.content
                    titleBorder.visibility = View.VISIBLE
                }) {
                    Log.e("tag", "error", it)
                    Toast.makeText(this, getString(R.string.connection_problem_text), Toast.LENGTH_LONG).show()
                }
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
}
