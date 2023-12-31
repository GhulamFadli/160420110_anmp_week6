package com.example.a160420110_anmp_w4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420110_anmp_w4.R
import com.example.a160420110_anmp_w4.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var detailView: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentId = arguments?.getString("studentId")

        detailView = ViewModelProvider(this).get(DetailViewModel::class.java)

        detailView.fetch(studentId.toString())
        observeViewModel()
    }
    fun observeViewModel() {
        val id = view?.findViewById<TextView>(R.id.txtId)
        val name = view?.findViewById<TextView>(R.id.txtName)
        val bod = view?.findViewById<TextView>(R.id.txtBod)
        val phone = view?.findViewById<TextView>(R.id.txtPhone)
        val imgPhoto = view?.findViewById<ImageView>(R.id.imageViewDet)
        detailView.studentLD.observe(viewLifecycleOwner, Observer { student ->
            id?.setText(student.id)
            name?.setText(student.name)
            bod?.setText(student.dob)
            phone?.setText(student.phone)
            Picasso.get().load(student.photoUrl).into(imgPhoto)
        })

        val btn = view?.findViewById<Button>(R.id.btnUpdate)
        btn?.setOnClickListener {
            Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    MainActivity.showNotif("New Notofication",
                        "Student is updated",
                        R.drawable.twotone_save_24)
                }
        }
    }
}