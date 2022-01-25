package uz.androdev.shared.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

private val ON_BOARDED = booleanPreferencesKey("on_boarded")
private val USERNAME = stringPreferencesKey("username")
private const val DATASTORE_NAME = "settings"
private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = DATASTORE_NAME
)

class DataStore @Inject constructor(@ApplicationContext private val context: Context) {
    fun isOnBoarded(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[ON_BOARDED] ?: false
        }
    }

    suspend fun setOnBoarded(onBoarded: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ON_BOARDED] = onBoarded
        }
    }

    fun getUserName(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[USERNAME] ?: "Foydalanuvchi"
        }
    }

    suspend fun setUserName(username: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }
}