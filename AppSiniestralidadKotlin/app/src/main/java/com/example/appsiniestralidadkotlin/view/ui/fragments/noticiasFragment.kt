package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.view.adapter.SiniestrosAdapter
import com.example.appsiniestralidadkotlin.viewModel.SiniestrosViewModel

class noticiasFragment : Fragment() {
    lateinit var recyclerSiniestro: RecyclerView
    lateinit var adapter: SiniestrosAdapter
    val viewmodel by lazy{ViewModelProvider(this).get(SiniestrosViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_noticias, container, false)
        recyclerSiniestro = view.findViewById(R.id.rv_noticias)
        adapter = SiniestrosAdapter(requireContext())
        recyclerSiniestro.layoutManager = LinearLayoutManager(context)
        recyclerSiniestro.adapter = adapter
        observeData()
        return view
    }

    fun observeData(){
        viewmodel.fetchSiniestroData().observe(viewLifecycleOwner, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}