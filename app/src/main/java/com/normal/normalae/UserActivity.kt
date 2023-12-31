package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.normal.normalae.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var bind: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityUserBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.bottomNav.selectedItemId = R.id.itemUser
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
                R.id.itemPrefs -> {
                    startActivity(Intent(this, PrefsActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
                R.id.itemUser -> {}
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