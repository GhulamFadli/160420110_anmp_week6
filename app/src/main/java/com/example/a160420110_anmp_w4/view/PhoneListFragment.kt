package com.example.a160420110_anmp_w4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

        val refreshLayoutPh = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutPh)

        observeViewModel()

        refreshLayoutPh.setOnRefreshListener {
            viewModel.refresh()
            refreshLayoutPh.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.phoneLD.observe(viewLifecycleOwner, Observer {
            adapter.updatePhoneList(it)
        })
        viewModel.phoneLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
            val recView = view?.findViewById<RecyclerView>(R.id.recViewPhone)
            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}