package com.company.product.Support


import android.content.Context
import android.content.SharedPreferences

class AppPrefrences(context: Context, prefsFile: String) {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun delete(key: String) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit()
        }
    }

    fun clearAll() {
        editor.clear()
        editor.commit()
    }

    @Synchronized
    fun savePref(key: String, value: Any?) {
        delete(key)
        if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor.putString(key, value as String?)
        } else if (value is Enum<*>) {
            editor.putString(key, value.toString())
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-primitive preference")
        }
        editor.commit()
    }

    fun <T> getPref(key: String): T {
        return sharedPreferences.all[key] as T
    }

    fun <T> getPref(key: String, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T
        return returnValue ?: defValue
    }

    fun isPrefExists(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    companion object {
        val FILE_DEVICE = "devicePrefs"
        val PREFS_DEVICE_TOKEN = "deviceToken"
        val PREFS_DEVICE_ID = "deviceID"
        val PREFS_BADGE_COUNT = "badgeCount"
        val PREFS_USER_ACCESS_TOKEN = "accessToken"

        val FILE_USER = "userPrefs"
        val PREFS_USER_ID = "userID"
        val PREF_USER_LOGIN_EMAIL = "email"
        val PREF_USER_LOGIN_PASSWORD = "password"
    }
}