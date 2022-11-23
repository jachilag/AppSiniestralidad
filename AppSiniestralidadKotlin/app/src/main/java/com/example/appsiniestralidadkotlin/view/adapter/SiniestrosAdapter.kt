package com.example.appsiniestralidadkotlin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.model.siniestros
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class SiniestrosAdapter(val context: Context, var clickListener: OnSiniestroItemClickListener):RecyclerView.Adapter<SiniestrosAdapter.ViewHolder>() {
    val db = FirebaseFirestore.getInstance()
    var siniestrosList = mutableListOf<siniestros>()
    fun setListData(data:MutableList<siniestros>){
        siniestrosList = data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_siniestro,
        ViewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val siniestro = siniestrosList[position]
        viewHolder.binWew(siniestro,clickListener)

    }

    override fun getItemCount(): Int {
        return siniestrosList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun binWew(siniestro: siniestros, action: OnSiniestroItemClickListener){
            val img = itemView.findViewById<ImageView>(R.id.iconoSiniestro)
            Picasso.get().load(siniestro.url).into(img)
            itemView.findViewById<TextView>(R.id.tituloSiniestro).text = siniestro.tipo
            itemView.findViewById<TextView>(R.id.tituloReportero).text = siniestro.reportero
            itemView.findViewById<TextView>(R.id.tituloFecha).text = siniestro.fecha

            itemView.setOnClickListener{
                action.onItemClickSiniestro(siniestro,adapterPosition)
            }
        }
    }

    interface OnSiniestroItemClickListener{
        fun onItemClickSiniestro(siniestro: siniestros,position:Int)
    }

}