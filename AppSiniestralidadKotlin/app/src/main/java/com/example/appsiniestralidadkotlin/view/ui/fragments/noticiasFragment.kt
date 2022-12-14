package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.model.siniestros
import com.example.appsiniestralidadkotlin.view.adapter.SiniestrosAdapter
import com.example.appsiniestralidadkotlin.viewModel.SiniestrosViewModel

class noticiasFragment : Fragment(), SiniestrosAdapter.OnSiniestroItemClickListener {
    lateinit var recyclerSiniestro: RecyclerView
    lateinit var adapter: SiniestrosAdapter
    lateinit var swipeNoticias: SwipeRefreshLayout
    val viewmodel by lazy{ ViewModelProvider(this)[SiniestrosViewModel::class.java] }



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_noticias, container, false)
        swipeNoticias = view.findViewById(R.id.swipeNoticias)
        recyclerSiniestro = view.findViewById(R.id.rv_noticias)
        adapter = SiniestrosAdapter(requireContext(),this)
        recyclerSiniestro.layoutManager = LinearLayoutManager(context)
        recyclerSiniestro.adapter = adapter
        observeData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeNoticias.setOnRefreshListener {
            observeData()
        }
    }

    fun observeData(){
        viewmodel.fetchSiniestroData().observe(viewLifecycleOwner, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
            swipeNoticias.isRefreshing = false

        })
    }

    override fun onItemClickSiniestro(siniestro: siniestros, position: Int) {
        val reportero:String? = siniestro.reportero
        val reporte:String? = siniestro.reporte
        val involucrados:String? = siniestro.tipo
        val fecha:String? = siniestro.fecha
        val txtLatitud:String? = siniestro.latitud
        val txtLongitud:String? = siniestro.longitud

        val bundle = bundleOf(
            "reportero" to reportero,
            "reporte" to reporte,
            "involucrados" to involucrados,
            "fecha" to fecha,
            "latitud" to txtLatitud,
            "longitud" to txtLongitud,
        )
        findNavController().navigate(R.id.action_noticiasFragment_to_detalleFragment,bundle)
    }


}