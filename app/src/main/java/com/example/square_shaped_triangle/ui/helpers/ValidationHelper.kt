package com.example.square_shaped_triangle.activity.helpers

import android.text.TextUtils

object ValidationHelper {

    fun validateEmailAndPassword(email:String, password: String): Boolean =
        !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
}