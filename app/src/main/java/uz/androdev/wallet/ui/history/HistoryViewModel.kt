package uz.androdev.wallet.ui.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.androdev.shared.repository.WalletRepository
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val walletRepository: WalletRepository
): ViewModel() {
    val transactions = walletRepository.getTransactions()
}