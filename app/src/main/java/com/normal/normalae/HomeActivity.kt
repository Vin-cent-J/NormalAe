package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.normal.normalae.databinding.ActivityHomeBinding
import com.normal.normalae.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    lateinit var bind: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        bind.recyclerView.layoutManager = lm
        bind.recyclerView.setHasFixedSize(true)
        bind.recyclerView.adapter = CerbungAdapter()

        bind.btnCreate.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }
}