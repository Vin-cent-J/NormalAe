package com.normal.normalae

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.normal.normalae.databinding.ActivityPrefsBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject


class PrefsActivity : AppCompatActivity() {
    private lateinit var bind: ActivityPrefsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPrefsBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val isDarkModeOn = sharedPreferences.getBoolean("Dark", false)

        bind.switchDark.isChecked = isDarkModeOn

        bind.switchDark.setOnCheckedChangeListener { compoundButton, checked ->
            val editor = sharedPreferences.edit()
            editor.putBoolean("Dark", checked);
            editor.apply();
            if(checked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        bind.txtPUser.text = Global.user?.username
        val url = Global.user?.url
        val builder = Picasso.Builder(this)
        with(bind) {
            builder.listener { picasso, url, exception -> exception.printStackTrace() }
            builder.build().load(url).into(imgProfile)
        }

        bind.fabOut.setOnClickListener{
            Global.user = null
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        bind.btnChange.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "https://ubaya.me/native/160421053/change.php"
            val stringRequest = object : StringRequest(Request.Method.POST, url, Response.Listener {
                Log.d("update", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK"){
                    Toast.makeText(this, "Password has been changed successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                Log.d("update", it.message.toString())
            })
            {
                override fun getParams(): MutableMap<String, String>? {
                     val params = HashMap<String, String>()
                    params["username"] = Global.user?.username.toString()
                    params["password"] = bind.txtNewPass.text.toString()
                    params["oldPass"] = bind.txtOldPass.text.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }

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