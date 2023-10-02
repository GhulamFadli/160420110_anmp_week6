package com.example.a160420110_anmp_w4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420110_anmp_w4.model.Phone
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class PhonesViewModel(application: Application):AndroidViewModel(application) {
    val phonesLD = MutableLiveData<ArrayList<Phone>>()
    val TAG = "volleyTagPhones"
    private var queue:RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/phones/phones.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Phone>>() { }.type
                val result = Gson().fromJson<List<Phone>>(it, sType)
                phonesLD.value = result as ArrayList<Phone>?
                Log.d("showvolley", result.toString())
            }, {
                Log.d("showvolley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}