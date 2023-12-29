package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.normal.normalae.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {
    private lateinit var bind: ActivityCreateBinding
    companion object{
        val TITLE = "com.normal.normalae.title"
        val DESC = "com.normal.normalae.desc"
        val URL = "com.normal.normalae.url"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCreateBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        val spinnerAdapter = ArrayAdapter(this, R.layout.spinner_layout, Global.genreList)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_layout)
        bind.spinGenre.adapter = spinnerAdapter

        bind.btnNext.setOnClickListener {
            val intent = Intent(this, CreatePage2Activty::class.java)
            intent.putExtra(TITLE, bind.txtTitle.text.toString())
            intent.putExtra(DESC, bind.txtDescription.text.toString())
            intent.putExtra(URL, bind.txtUrlCover.text.toString())
            startActivity(intent)
        }
    }
}