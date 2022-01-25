package uz.androdev.wallet.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.androdev.shared.model.Category
import uz.androdev.shared.model.CategoryType
import uz.androdev.shared.repository.PreferencesRepository
import uz.androdev.shared.repository.WalletRepository
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {
    val username = preferencesRepository.getUserName()

    fun setUserName(username: String) = viewModelScope.launch(Dispatchers.IO) {
        preferencesRepository.setUserName(username)
    }

    fun addCategory(title: String, type: CategoryType) = viewModelScope.launch(Dispatchers.IO) {
        walletRepository.insertCategory(
            Category(
                title = title,
                type = type
            )
        )
    }
}