package com.example.square_shaped_triangle.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val login: String,
    val email: String,
    val password: String
) : Parcelable