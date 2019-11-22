package com.example.square_shaped_triangle.activity.helpers.singincontroller

import android.content.Intent

interface EventCallback {
    fun onError(error: String)
    fun startActivityForResult(signInIntent: Intent)
    fun updateUI()
}