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
            txtPar.text = cerbung.paragraph[position].text
            txtWriter.text = "By: " + cerbung.paragraph[position].user_username
        }
    }
}