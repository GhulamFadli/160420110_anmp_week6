package com.example.a160420110_anmp_w4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420110_anmp_w4.R
import com.example.a160420110_anmp_w4.viewmodel.PhonesViewModel

class PhoneListFragment : Fragment() {
    private lateinit var viewModel:PhonesViewModel
    private val adapter = PhoneListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhonesViewModel::class.java)
        viewModel.refresh()

        val recViewPhone = view.findViewById<RecyclerView>(R.id.recViewPhone)
        recViewPhone.layoutManager = LinearLayoutManager(context)
        recViewPhone.adapter = adapter

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.phonesLD.observe(viewLifecycleOwner, Observer{
            adapter.updatePhoneList(it)
        })
    }
}