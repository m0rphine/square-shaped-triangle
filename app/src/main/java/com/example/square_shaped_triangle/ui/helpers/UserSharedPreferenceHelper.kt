package com.example.square_shaped_triangle.activity.helpers

import android.content.Context
import android.content.SharedPreferences

class UserSharedPreferenceHelper(context: Context) {
    private val sharedPreference: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var userId: String?
        get() = sharedPreference.getString(USER_ID, null)
        set(value) {
            sharedPreference.editMe {
                it.putString(USER_ID, value)
            }
        }

    var password: String?
        get() = sharedPreference.getString(USER_PASSWORD, null)
        set(value) {
            sharedPreference.editMe {
                it.putString(USER_PASSWORD, value)
            }
        }

    fun clearValues() = sharedPreference.editMe { it.clear() }

    companion object{
        private const val USER_ID = "USER_ID"
        private const val USER_PASSWORD = "USER_PASSWORD"
        private const val SHARED_PREF_NAME = "MY_SHARED_PREF"

        fun newInstance(context: Context) = UserSharedPreferenceHelper(context)
    }
}