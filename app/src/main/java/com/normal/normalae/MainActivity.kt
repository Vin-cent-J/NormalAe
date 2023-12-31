package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.normal.normalae.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val isDark = sharedPreferences.getBoolean("Dark", false)
        if(isDark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        bind.btnSignin.setOnClickListener {
            var user = User(bind.txtUsername.text.toString(), bind.txtPassword.text.toString(), "")
            val q = Volley.newRequestQueue(this)
            val url = "https://ubaya.me/native/160421053/login.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> {
                    Log.d("cekparams", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")
                        val sType = object : TypeToken<ArrayList<User>>() {}.type
                        Global.users  = Gson().fromJson(data.toString(), sType) as
                                ArrayList<User>
                        Global.user = Global.users[0];
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString())
                })
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = user.username
                    params["password"] = user.password

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