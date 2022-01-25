package uz.androdev.shared.repository

import uz.androdev.shared.preferences.DataStore
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class PreferencesRepository @Inject constructor(
    private val dataStore: DataStore
) {
    fun isOnBoarded() = dataStore.isOnBoarded()
    suspend fun setOnBoarded(onBoarded: Boolean) = dataStore.setOnBoarded(onBoarded)

    fun getUserName() = dataStore.getUserName()
    suspend fun setUserName(username: String) = dataStore.setUserName(username)
}