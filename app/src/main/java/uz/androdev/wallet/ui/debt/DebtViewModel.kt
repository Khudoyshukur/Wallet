package uz.androdev.wallet.ui.debt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.androdev.shared.model.Expense
import uz.androdev.shared.model.Income
import uz.androdev.shared.repository.WalletRepository
import java.util.*
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@HiltViewModel
class DebtViewModel @Inject constructor(
    private val walletRepository: WalletRepository
) : ViewModel() {
    fun removeDebt(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        walletRepository.removeDebt(expense)
    }

    val debts = walletRepository.getDebts()
}