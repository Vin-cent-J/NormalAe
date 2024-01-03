package com.normal.normalae

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.normal.normalae.databinding.CerbungItemBinding
import com.normal.normalae.databinding.ParagraphItemBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class ParagraphAdapter(val cerbung: Cerbung): RecyclerView.Adapter<ParagraphAdapter.ParagraphViewHolder>() {
    class ParagraphViewHolder(val bind: ParagraphItemBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagraphViewHolder{
        val bind= ParagraphItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParagraphViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return cerbung.paragraph.size
    }

    override fun onBindViewHolder(holder: ParagraphViewHolder, position: Int) {
        with(holder.bind) {
            btnLikeP.text = cerbung.paragraph[position].likes.toString()
            txtPar.text = cerbung.paragraph[position].text
            txtWriter.text = "By " + cerbung.paragraph[position].user_username

            btnLikeP.setOnClickListener {
                val q = Volley.newRequestQueue(holder.itemView.context)
                val url = "https://ubaya.me/native/160421053/like_paragraph.php"
                val stringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener {
                        Log.d("cekparams", it)
                        val obj = JSONObject(it)
                        if(obj.getString("result") == "OK"){
                            cerbung.paragraph[position].likes++
                            var newFol = cerbung.paragraph[position].likes
                            holder.bind.btnLikeP.text = "$newFol"
                        }else{
                            cerbung.paragraph[position].likes--
                            var newFol = cerbung.paragraph[position].likes
                            holder.bind.btnLikeP.text = "$newFol"
                        }
                    },
                    Response.ErrorListener {
                        Log.d("cekparams", it.message.toString())
                    })
                {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["id"] = cerbung.paragraph[position].id.toString()

                        return params
                    }
                }
                q.add(stringRequest)
            }
        }
    }
}