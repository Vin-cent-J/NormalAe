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
import com.normal.normalae.databinding.ActivityFollowBinding
import org.json.JSONObject

class FollowActivity : AppCompatActivity() {
    private lateinit var bind: ActivityFollowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityFollowBinding.inflate(layoutInflater)
        setContentView(bind.root)

        var cerbungs: ArrayList<Cerbung> = ArrayList()
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421053/followed_cerbung.php"
        var stringRequest = object:StringRequest(Request.Method.POST, url,
            Response.Listener {
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
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = Global.user?.username.toString()
                return params
            }
        }
        q.add(stringRequest)

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        bind.recyclerFoll.layoutManager = lm
        bind.recyclerFoll.setHasFixedSize(true)
        bind.recyclerFoll.adapter = FollowedAdapter(cerbungs)

        bind.bottomNav.selectedItemId = R.id.itemFollow

        bind.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.itemHome -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
                R.id.itemFollow -> {}
                R.id.itemPrefs -> {
                    startActivity(Intent(this, PrefsActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
                R.id.itemUser -> {
                    startActivity(Intent(this, UserActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
                else -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                }
            }
            true
        }
    }
    fun updateList(cerbungs: ArrayList<Cerbung>) {
        val lm = LinearLayoutManager(this)
        with(bind.recyclerFoll) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = FollowedAdapter(cerbungs)
        }
    }
}