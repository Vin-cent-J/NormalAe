package com.normal.normalae

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.normal.normalae.databinding.FollowedItemBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class FollowedAdapter(var cerbungs: ArrayList<Cerbung>): RecyclerView.Adapter<FollowedAdapter.CerbungViewHolder>() {
    class CerbungViewHolder(val bind: FollowedItemBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerbungViewHolder{
        val bind = FollowedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            builder.build().load(url).into(imgFCerbung)
            lblFTitle.text=cerbungs[position].title
            lblFWriter.text= "By: "+cerbungs[position].user_username
        }

        holder.bind.cardFollow.setOnClickListener {
            startActivity(holder.itemView.context, intent, null)
        }
    }
}