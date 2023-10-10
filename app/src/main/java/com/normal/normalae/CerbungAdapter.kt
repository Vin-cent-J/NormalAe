package com.normal.normalae

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.normal.normalae.databinding.CerbungItemBinding
import com.squareup.picasso.Picasso
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity

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
        intent.putExtra(ID, position)
        with(holder.bind){
            builder.build().load(url).into(imgCerbung)
            lblTitle.text=Global.cerbungs[position].title
            lblDesc.text=Global.cerbungs[position].description
        }

        holder.bind.btnRead.setOnClickListener {
            startActivity(holder.itemView.context, intent, null)
        }
    }
}