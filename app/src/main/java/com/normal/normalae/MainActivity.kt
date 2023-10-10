package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.normal.normalae.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        bind.btnSignin.setOnClickListener {
            if(bind.txtPassword.text.toString() == Global.users[0].password
                && bind.txtUsername.text.toString() == Global.users[0].username){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Wrong password or username.", Toast.LENGTH_SHORT).show()
            }
        }
        bind.btnSignup2.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}