package com.example.a160420110_anmp_w4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420110_anmp_w4.R
import com.example.a160420110_anmp_w4.model.Phone

class PhoneListAdapter(val phones:ArrayList<Phone>)
    :RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder>() {
    class PhoneViewHolder(v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.phone_list_item, parent, false)
        return PhoneViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        var txtResult =
            holder.itemView.findViewById<TextView>(R.id.txtResult)
        var brand = phones[position].brand
        var model = phones[position].model
        var releaseYear = phones[position].releaseYear
        txtResult.text = "$brand, $model, $releaseYear"
    }
    fun updatePhoneList(newPhoneList: ArrayList<Phone>) {
        phones.clear()
        phones.addAll(newPhoneList)
        notifyDataSetChanged()
    }
}