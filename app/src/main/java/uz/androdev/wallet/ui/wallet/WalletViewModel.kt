package uz.androdev.wallet.ui.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import uz.androdev.shared.model.Wallet
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
class WalletViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val walletRepository: WalletRepository,
) : ViewModel() {
    val username = preferencesRepository.getUserName()
    val wallet = walletRepository.getWallet()

    fun isOnBoarded(): Boolean {
        return runBlocking { preferencesRepository.isOnBoarded().first() }
    }
}