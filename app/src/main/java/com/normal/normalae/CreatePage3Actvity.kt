package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.normal.normalae.databinding.ActivityCreatePage3ActvityBinding

class CreatePage3Actvity : AppCompatActivity() {
    private lateinit var bind: ActivityCreatePage3ActvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCreatePage3ActvityBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)


        val intent = Intent(this, HomeActivity::class.java)
        val prev = Intent(this, CreatePage3Actvity::class.java)

        val TITLE = CreateActivity.TITLE
        val title = getIntent().getStringExtra(TITLE)

        val DESC = CreateActivity.DESC
        val desc = getIntent().getStringExtra(DESC)

        val PAR = CreatePage2Activty.PAR
        val par = getIntent().getStringExtra(PAR)

        with(bind){
            txtCheckDesc.text = desc
            txtCheckPar.text = par
            txtCheckTitle.text = title
        }

        bind.btnPrev3.setOnClickListener {
            startActivity(prev)
        }

        bind.btnPublish.setOnClickListener {
            startActivity(intent)
        }
    }
}