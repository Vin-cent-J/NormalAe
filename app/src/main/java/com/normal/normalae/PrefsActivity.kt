package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.normal.normalae.databinding.ActivityPrefsBinding

class PrefsActivity : AppCompatActivity() {
    private lateinit var bind: ActivityPrefsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPrefsBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.bottomNav.selectedItemId = R.id.itemPrefs
        bind.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.itemHome -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
                R.id.itemFollow -> {
                    startActivity(Intent(this, FollowActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
                R.id.itemPrefs -> {}
                else -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
            }
            true
        }
    }
}