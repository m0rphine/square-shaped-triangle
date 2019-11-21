package com.example.square_shaped_triangle.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    //val name: String,
    val email: String,
    val password: String
    //,
    //val photoUrl: Uri
) : Parcelable