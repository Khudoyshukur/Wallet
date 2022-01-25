package uz.androdev.wallet.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.androdev.shared.model.Category
import uz.androdev.shared.model.Expense
import uz.androdev.shared.model.Income
import uz.androdev.shared.repository.WalletRepository
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    fun fetchCategories(type: TransactionType) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(uiState.value.copy(loading = true))
            val categories = when (type) {
                TransactionType.Expense -> {
                    walletRepository.getExpenseCategoriesBlocking()
                }
                TransactionType.Income -> {
                    walletRepository.getIncomeCategoriesBlocking()
                }
            }

            _uiState.emit(uiState.value.copy(loading = false, categories = categories))
        }
    }

    fun enterTransaction(
        details: String,
        amount: Double,
        categoryName: String,
        type: TransactionType,
        onComplete: () -> Unit,
        onNotEnoughMoney: () -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
        when (type) {
            TransactionType.Expense -> {
                try {
                    walletRepository.insertExpense(
                        Expense(
                            categoryId = uiState.value.categories.find { it.title == categoryName }?.id!!,
                            details = details,
                            datetime = Calendar.getInstance().time,
                            expense = amount,
                            walletId = walletRepository.getWalletBlocking().id!!,
                            isDebt = categoryName == "Qarz"
                        )
                    )
                } catch (e: IllegalArgumentException) {
                    withContext(Dispatchers.Main) {
                        onNotEnoughMoney.invoke()
                    }
                    return@launch
                }
            }
            TransactionType.Income -> {
                walletRepository.insertIncome(
                    Income(
                        categoryId = uiState.value.categories.find { it.title == categoryName }?.id!!,
                        details = details,
                        datetime = Calendar.getInstance().time,
                        income = amount,
                        walletId = walletRepository.getWalletBlocking().id!!,
                    )
                )
            }
        }

        withContext(Dispatchers.Main) {
            onComplete.invoke()
        }
    }
}

data class UiState(
    val loading: Boolean = false,
    val categories: List<Category> = emptyList()
)