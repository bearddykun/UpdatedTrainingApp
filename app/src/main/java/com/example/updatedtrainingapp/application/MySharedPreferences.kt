package com.example.updatedtrainingapp.application

import org.jetbrains.anko.defaultSharedPreferences

class MySharedPreferences {

    companion object {
        private val sharedPreferencesEditor = ThisApplication.instance.defaultSharedPreferences.edit()
        private val sharedPreferences = ThisApplication.instance.defaultSharedPreferences

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

        fun isInside(key: String): Boolean {
            return sharedPreferences.contains(key)
        }

        fun saveString(key: String, string: String){
            sharedPreferencesEditor.putString(key, string)
            sharedPreferencesEditor.apply()
        }

        fun getString(key: String): String{
            return sharedPreferences.getString(key, "")!!
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