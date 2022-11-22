package com.example.appsiniestralidadkotlin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.model.Ciudad

class CiudadesAdaptador(val context: Context): RecyclerView.Adapter<CiudadesAdaptador.ViewHolder>() {
    var CiudadList = mutableListOf<Ciudad>()

    fun setListData(data: MutableList<Ciudad>) {
        CiudadList = data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context).inflate(
            R.layout.card_view_siniestro,
            ViewGroup, false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val ciudad = CiudadList[position]
        viewHolder.binWew(ciudad)

    }

    override fun getItemCount(): Int {
        return CiudadList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun binWew(ciudad: Ciudad) {


        }
    }
}