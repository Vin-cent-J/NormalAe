package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.normal.normalae.databinding.ActivityCreatePage3ActvityBinding

class CreatePage3Actvity : AppCompatActivity() {
    private lateinit var bind: ActivityCreatePage3ActvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCreatePage3ActvityBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)


        val intent = Intent(this, HomeActivity::class.java)
        val prev = Intent(this, CreatePage3Actvity::class.java)

        val TITLE = CreateActivity.TITLE
        val title = getIntent().getStringExtra(TITLE)

        val DESC = CreateActivity.DESC
        val desc = getIntent().getStringExtra(DESC)

        val PAR = CreatePage2Activty.PAR
        val par = getIntent().getStringExtra(PAR)

        val RAD = CreatePage2Activty.RAD
        val rad = getIntent().getIntExtra(RAD, 1)

        with(bind){
            txtCheckDesc.text = desc
            txtCheckPar.text = par
            txtCheckTitle.text = title
        }

        bind.btnPrev3.setOnClickListener {
            startActivity(prev)
        }

        bind.btnPublish.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "https://ubaya.me/native/160421053/add_cerbung.php"
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
                    params["user"] = Global.user?.username.toString()
                    params["title"] = title.toString()
                    params["desc"] = desc.toString()
                    params["url"] = url
                    params["public"] = rad.toString()
                    params["paragraph"] = par.toString()
                    return params
                }
            }
            q.add(stringRequest)
            startActivity(intent)
        }
    }
}