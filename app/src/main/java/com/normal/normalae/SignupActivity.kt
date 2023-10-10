package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.normal.normalae.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var bind: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySignupBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        bind.btnSignup.setOnClickListener {
            if(bind.txtUName.text.isEmpty() || bind.txtPwd.text.isEmpty() || bind.txtPwdConfirmation.text.isEmpty()){
                Toast.makeText(this, "Fill in the fields", Toast.LENGTH_SHORT).show()
            }
            else if(bind.txtPwd.text.toString() == bind.txtPwdConfirmation.text.toString()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Passwords does not match.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}