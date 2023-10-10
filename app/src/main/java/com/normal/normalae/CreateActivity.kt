package com.normal.normalae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.normal.normalae.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {
    lateinit var bind: ActivityCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCreateBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        val spinnerAdapter = ArrayAdapter(this, R.layout.spinner_layout, Global.genreList)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_layout)

    }
}