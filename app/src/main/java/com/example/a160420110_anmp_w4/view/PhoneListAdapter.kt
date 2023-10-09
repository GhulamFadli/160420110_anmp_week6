package com.example.a160420110_anmp_w4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420110_anmp_w4.R
import com.example.a160420110_anmp_w4.model.Phone

class PhoneListAdapter(val phones:ArrayList<Phone>)
    :RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder>() {
    class PhoneViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtBrand: TextView
        val txtModel: TextView
        val txtPrice: TextView
        val txtColor: TextView
        val photoPhone: ImageView
        init {
            txtBrand = view.findViewById(R.id.txtBrand)
            txtModel = view.findViewById(R.id.txtModel)
            txtPrice = view.findViewById(R.id.txtPrice)
            txtColor = view.findViewById(R.id.txtColor)
            photoPhone = view.findViewById(R.id.imageViewPhone)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.phone_list_item, parent, false)
        return PhoneViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.txtBrand.text = "Brand: " + phones[position].brand
        holder.txtModel.text = "Model: " + phones[position].model
        holder.txtColor.text = "Color: " + phones[position].color
        holder.txtPrice.text = "Price: " + phones[position].price.toString()
//        holder.photoPhone.setImageResource(phones[position].photoPhone.hashCode())
    }
    fun updatePhoneList(newPhoneList: ArrayList<Phone>) {
        phones.clear()
        phones.addAll(newPhoneList)
        notifyDataSetChanged()
    }
}