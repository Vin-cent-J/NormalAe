package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.normal.normalae.databinding.ActivityCreatePage2ActivtyBinding

class CreatePage2Activty : AppCompatActivity() {
    private lateinit var bind: ActivityCreatePage2ActivtyBinding

    companion object{
        val PAR = "com.normal.normalae.par"
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

        bind.btnNext2.setOnClickListener {
            intent.putExtra(TITLE, title)
            intent.putExtra(DESC, title)
            intent.putExtra(PAR, bind.txtParagraph.text.toString())
            startActivity(intent)
        }

        bind.btnPrev2.setOnClickListener {
            startActivity(prev)
        }
    }
}