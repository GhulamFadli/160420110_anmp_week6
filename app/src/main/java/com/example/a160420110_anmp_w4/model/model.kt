package com.example.a160420110_anmp_w4.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val dob:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String?
)
data class Phone(
    val id:Int?,
    val brand:String?,
    val model:String?,
    val releaseYear:Int?,
    val color:String?,
    val price:Double?,
    val storageGB:Int,
    val photoPhone:String?,
    val features:List<String>,
    val cameraSpecs:Specs?
)
data class Specs(
    val mainCamera:String?,
    val ultrawideCamera:String?,
    val telephotoamera:String?
)
