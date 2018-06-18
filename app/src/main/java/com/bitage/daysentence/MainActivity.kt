package com.bitage.daysentence

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bitage.daysentence.adapter.SentenceAdapter
import com.bitage.daysentence.dao.ISentenceDAO
import com.bitage.daysentence.dao.SentenceCallback
import com.bitage.daysentence.dao.SentenceDAO
import com.bitage.daysentence.dto.SentenceDTO
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sentenceList.layoutManager = LinearLayoutManager(this)

        sendButton.setOnClickListener {
            val sentenceDAO = SentenceDAO()
            sentenceDAO.fetch("*", object: SentenceCallback {
                override fun onFailure(call: Call<List<SentenceDTO>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<List<SentenceDTO>>, response: Response<List<SentenceDTO>>) {
                    response.body()?.let {
                        sentenceList.adapter = SentenceAdapter(it)
                    }
                }

            })
        }
    }
}
