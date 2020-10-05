package com.example.updatedtrainingapp.application

import android.content.SharedPreferences

private const val NAME = "myPrefs"

class MySharedPreferences {

    companion object {
        private val sharedPreferencesEditor: SharedPreferences.Editor =
            ThisApplication.instance.getSharedPreferences(NAME, 0).edit()
        private val sharedPreferences = ThisApplication.instance.getSharedPreferences(NAME, 0)

        fun saveBoolean(key: String, boolean: Boolean) {
            sharedPreferencesEditor.putBoolean(key, boolean)
            sharedPreferencesEditor.apply()
        }

        fun getBoolean(key: String): Boolean {
            return sharedPreferences.getBoolean(key, false)
        }

        fun saveLong(key: String, long: Long) {
            sharedPreferencesEditor.putLong(key, long)
            sharedPreferencesEditor.apply()
        }

        fun getLong(key: String): Long {
            return sharedPreferences.getLong(key, 0)
        }

        fun saveInt(key: String, int: Int) {
            sharedPreferencesEditor.putInt(key, int)
            sharedPreferencesEditor.apply()
        }

        fun getInt(key: String): Int {
            return sharedPreferences.getInt(key, 0)
        }

        fun isInside(key: String): Boolean {
            return sharedPreferences.contains(key)
        }

        fun saveString(key: String, string: String) {
            sharedPreferencesEditor.putString(key, string)
            sharedPreferencesEditor.apply()
        }

        fun getString(key: String): String {
            return sharedPreferences.getString(key, "") as String
        }

        fun getList(listKey: String): MutableSet<String> {
            return sharedPreferences.getStringSet(listKey, mutableSetOf()) as MutableSet<String>
        }

        fun saveList(key: String, set: Set<String>) {
            sharedPreferencesEditor.putStringSet(key, set)
            sharedPreferencesEditor.apply()
        }
    }
}