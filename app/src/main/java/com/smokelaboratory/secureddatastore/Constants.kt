package com.smokelaboratory.secureddatastore

import androidx.datastore.preferences.core.stringPreferencesKey

class Constants {

    object DataStore {
        val DATA = stringPreferencesKey("data")
        val SECURED_DATA = stringPreferencesKey("secured_data")
    }
}