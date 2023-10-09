package com.example.a160420110_anmp_w4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420110_anmp_w4.model.Phone
import com.example.a160420110_anmp_w4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class PhonesViewModel(application: Application)
    : AndroidViewModel(application) {
    val phoneLD = MutableLiveData<ArrayList<Phone>>()
    val phoneLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTagPhones"
    private var queue:RequestQueue? = null

    fun refresh() {
        phoneLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/phones/phones.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Phone>>() { }.type
                val result = Gson().fromJson<List<Phone>>(it, sType)
                phoneLD.value = result as ArrayList<Phone>?
                loadingLD.value = false
                Log.d("showvolley", result.toString())
            }, {
                Log.d("showvolley", it.toString())
                phoneLoadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.retryPolicy = DefaultRetryPolicy(
            6000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}