package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.set
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.normal.normalae.databinding.ActivityReadBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReadActivity : AppCompatActivity() {
    private lateinit var bind: ActivityReadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityReadBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        val id = intent.getIntExtra(CerbungAdapter.ID, 0)

        val lm = LinearLayoutManager(this)

        var cerbung: Cerbung? = null
        var access: String? = null
        val q = Volley.newRequestQueue(this)
        val uri = "https://ubaya.me/native/160421053/read_cerbung.php"
        val stringRequest = object : StringRequest(Request.Method.POST, uri,
            Response.Listener {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<ArrayList<Cerbung>>() {}.type
                    val cerbungs = Gson().fromJson(data.toString(), sType) as ArrayList<Cerbung>
                    cerbung = cerbungs[0]

                    val item = data.getJSONObject(0)
                    access = item.getString("acc")

                    val url = cerbung?.url
                    val builder = Picasso.Builder(this)
                    with(bind){
                        builder.listener{picasso, url, exception->exception.printStackTrace()}
                        builder.build().load(url).into(imageView)
                        lblTitleRead.text = cerbung?.title
                        txtRWriter.text = "By "+cerbung?.user_username

                        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        val date = LocalDate.parse( cerbung?.date, format)
                        txtDate.text = date.dayOfMonth.toString()+"/"+date.monthValue+"/"+date.year

                        recyclerParagraph.layoutManager = lm
                        recyclerParagraph.adapter= cerbung?.let{ParagraphAdapter(it)}

                        if((cerbung?.public == 0) && (Global.user?.username != cerbung?.user_username)
                            && (access == "null")){
                            btnSubmit.text = "Request Access"
                        }
                    }
                    updateList(cerbung!!)
                }
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = id.toString();
                return params
            }
        }
        q.add(stringRequest)

        bind.btnSubmit.setOnClickListener {
            if (access == "null") {
                Toast.makeText(this, "Access Requested", Toast.LENGTH_SHORT).show()
            } else {
                val txt = bind.txtContinue.text
                val q = Volley.newRequestQueue(this)
                val url = "https://ubaya.me/native/160421053/add_paragraph.php"
                val stringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener {
                        Log.d("cekparams", it)
                        val obj = JSONObject(it)
                        if (obj.getString("result") == "OK") {
                            val par = Paragraph(obj.getString("data").toInt(),
                                Global.user!!.username,
                                id,
                                bind.txtContinue.text.toString(),
                                0
                            )
                            cerbung!!.paragraph.add(par)
                            updateList(cerbung!!)
                            bind.txtContinue.setText("")
                        }
                    },
                    Response.ErrorListener {
                        Log.d("cekparams", it.message.toString())
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["id"] = id.toString()
                        params["username"] = Global.user?.username.toString()
                        params["text"] = bind.txtContinue.text.toString()

                        return params
                    }
                }
                q.add(stringRequest)
            }
        }


    }

    fun updateList(cerbung: Cerbung) {
        val lm = LinearLayoutManager(this)
        with(bind.recyclerParagraph) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = ParagraphAdapter(cerbung)
        }
    }
}