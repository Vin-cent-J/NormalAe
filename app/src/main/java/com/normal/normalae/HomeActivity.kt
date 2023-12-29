package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.normal.normalae.databinding.ActivityHomeBinding
import com.normal.normalae.databinding.ActivityMainBinding
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    lateinit var bind: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        var cerbungs: ArrayList<Cerbung> = ArrayList()
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421053/get_cerbung.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<ArrayList<Cerbung>>() {}.type
                    cerbungs = Gson().fromJson(data.toString(), sType) as
                            ArrayList<Cerbung>
                    updateList(cerbungs)
                }
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            })
        q.add(stringRequest)

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        bind.recyclerView.layoutManager = lm
        bind.recyclerView.setHasFixedSize(true)
        bind.recyclerView.adapter = CerbungAdapter(cerbungs)

        bind.btnCreate.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    fun updateList(cerbungs: ArrayList<Cerbung>) {
        val lm = LinearLayoutManager(this)
        with(bind.recyclerView) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = CerbungAdapter(cerbungs)
        }
    }
}