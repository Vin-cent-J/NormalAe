package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.normal.normalae.databinding.ActivityReadBinding
import com.squareup.picasso.Picasso

class ReadActivity : AppCompatActivity() {
    private lateinit var bind: ActivityReadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityReadBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        val id = intent.getIntExtra(CerbungAdapter.ID, 0)
        val url = Global.cerbungs[id].url
        val builder = Picasso.Builder(this)

        with(bind){
            builder.listener{picasso, url, exception->exception.printStackTrace()}
            builder.build().load(url).into(imageView)
            lblTitleRead.text = Global.cerbungs[id].title
            txtReadParagraph.text = Global.cerbungs[id].paragraph[0].text
        }
    }
}