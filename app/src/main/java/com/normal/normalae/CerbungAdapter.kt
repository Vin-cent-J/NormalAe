package com.normal.normalae

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.normal.normalae.databinding.CerbungItemBinding
import com.squareup.picasso.Picasso
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class CerbungAdapter():RecyclerView.Adapter<CerbungAdapter.CerbungViewHolder>() {
    class CerbungViewHolder(val bind: CerbungItemBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerbungViewHolder{
        val bind= CerbungItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CerbungViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return Global.cerbungs.size
    }

    companion object{
        val ID = "com.normal.normalae.id"
    }

    override fun onBindViewHolder(holder: CerbungViewHolder, position: Int) {
        val url = Global.cerbungs[position].url
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, url, exception->exception.printStackTrace()}

        val intent = Intent(holder.itemView.context, ReadActivity::class.java)
        intent.putExtra(ID, holder.adapterPosition)
        with(holder.bind){
            builder.build().load(url).into(imgCerbung)
            lblTitle.text=Global.cerbungs[position].title
            lblDesc.text=Global.cerbungs[position].desc
            lblUser.text= "By: "+Global.cerbungs[position].user_username
            btnLike.text=Global.cerbungs[position].likes.toString()
        }

        holder.bind.btnRead.setOnClickListener {
            startActivity(holder.itemView.context, intent, null)
        }
        holder.bind.btnLike.setOnClickListener {
            val q = Volley.newRequestQueue(holder.itemView.context)
            val url = "https://ubaya.me/native/160421053/add_like.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("cekparams", it)
                    Global.cerbungs[position].likes++
                    var newlikes = Global.cerbungs[holder.adapterPosition].likes
                    holder.bind.btnLike.text = "$newlikes"
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString())
                })
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id"] = Global.cerbungs[holder.adapterPosition].id.toString()

                    return params
                }
            }
            q.add(stringRequest)
        }
    }
}