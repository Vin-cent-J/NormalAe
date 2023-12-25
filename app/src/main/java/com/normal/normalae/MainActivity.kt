package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.normal.normalae.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        bind.btnSignin.setOnClickListener {
            var user = User(bind.txtPassword.text.toString(), bind.txtUsername.text.toString())
            val q = Volley.newRequestQueue(this)
            val url = "https://ubaya.me/native/160421053/login.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("cekparams", it)
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString())
                })
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = bind.txtUsername.text.toString()
                    params["password"] = bind.txtPassword.text.toString()

                    return params
                }
            }
            q.add(stringRequest)
        }
        bind.btnSignup2.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}