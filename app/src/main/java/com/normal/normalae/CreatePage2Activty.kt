package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.normal.normalae.databinding.ActivityCreatePage2ActivtyBinding

class CreatePage2Activty : AppCompatActivity() {
    private lateinit var bind: ActivityCreatePage2ActivtyBinding

    companion object{
        val PAR = "com.normal.normalae.par"
        val RAD = "com.normal.normalae.rad"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityCreatePage2ActivtyBinding.inflate(layoutInflater)
        val view = bind.root

        setContentView(view)

        val intent = Intent(this, CreatePage3Actvity::class.java)
        val prev = Intent(this, CreateActivity::class.java)


        val TITLE = CreateActivity.TITLE
        val title = getIntent().getStringExtra(TITLE)

        val DESC = CreateActivity.DESC
        val desc = getIntent().getStringExtra(DESC)

        val URL = CreateActivity.URL
        val url = getIntent().getStringExtra(URL)

        var public = 0
        if(bind.rdPublic.isChecked){
            public = 1
        }

        bind.btnNext2.setOnClickListener {
            intent.putExtra(TITLE, title)
            intent.putExtra(DESC, desc)
            intent.putExtra(URL, url)
            intent.putExtra(PAR, bind.txtParagraph.text.toString())
            intent.putExtra(RAD, public)
            startActivity(intent)
        }

        bind.btnPrev2.setOnClickListener {
            startActivity(prev)
        }
    }
}