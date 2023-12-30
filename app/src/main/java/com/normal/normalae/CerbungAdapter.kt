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
import org.json.JSONObject

class CerbungAdapter(var cerbungs: ArrayList<Cerbung>):RecyclerView.Adapter<CerbungAdapter.CerbungViewHolder>() {
    class CerbungViewHolder(val bind: CerbungItemBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerbungViewHolder{
        val bind= CerbungItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CerbungViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return cerbungs.size
    }

    companion object{
        val ID = "com.normal.normalae.id"
    }

    override fun onBindViewHolder(holder: CerbungViewHolder, position: Int) {
        val url = cerbungs[position].url
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, url, exception->exception.printStackTrace()}

        val intent = Intent(holder.itemView.context, ReadActivity::class.java)
        intent.putExtra(ID, cerbungs[holder.adapterPosition].id)
        with(holder.bind){
            builder.build().load(url).into(imgCerbung)
            lblTitle.text=cerbungs[position].title
            lblDesc.text=cerbungs[position].desc
            lblUser.text= "By: "+cerbungs[position].user_username
            btnLike.text=cerbungs[position].likes.toString()
            btnFol.text=cerbungs[position].follow.toString()
        }

        holder.bind.btnRead.setOnClickListener {
            startActivity(holder.itemView.context, intent, null)
        }
        holder.bind.btnFol.setOnClickListener {
            val q = Volley.newRequestQueue(holder.itemView.context)
            val url = "https://ubaya.me/native/160421053/follow.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("cekparams", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK"){
                        cerbungs[position].follow++
                        var newFol = cerbungs[holder.adapterPosition].follow
                        holder.bind.btnFol.text = "$newFol"
                    }else{
                        cerbungs[position].follow--
                        var newFol = cerbungs[holder.adapterPosition].follow
                        holder.bind.btnFol.text = "$newFol"
                    }
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString())
                })
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id"] = cerbungs[holder.adapterPosition].id.toString()
                    params["username"] = Global.user?.username.toString()

                    return params
                }
            }
            q.add(stringRequest)
        }
        holder.bind.btnLike.setOnClickListener {
            val q = Volley.newRequestQueue(holder.itemView.context)
            val url = "https://ubaya.me/native/160421053/add_like.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("cekparams", it)
                    cerbungs[position].likes++
                    var newlikes = cerbungs[holder.adapterPosition].likes
                    holder.bind.btnLike.text = "$newlikes"
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString())
                })
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id"] = cerbungs[holder.adapterPosition].id.toString()

                    return params
                }
            }
            q.add(stringRequest)
        }
    }
}