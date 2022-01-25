package uz.androdev.shared.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androdev.shared.datasource.WalletDataSource
import uz.androdev.shared.mapper.toWallet
import uz.androdev.shared.model.Category
import uz.androdev.shared.model.Expense
import uz.androdev.shared.model.Income
import uz.androdev.shared.model.Wallet
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class WalletRepository @Inject constructor(
    private val walletDataSource: WalletDataSource
) {

    fun getWallet(): Flow<Wallet> {
        return walletDataSource.getWallet().map { it.toWallet() }
    }

    fun getExpenseCategories(): LiveData<List<Category>> {
        return walletDataSource.getExpenseCategories()
    }

    fun getIncomeCategories(): LiveData<List<Category>> {
        return walletDataSource.getIncomeCategories()
    }

    fun getExpenseCategoriesBlocking(): List<Category> {
        return walletDataSource.getExpenseCategoriesBlocking()
    }

    fun getIncomeCategoriesBlocking(): List<Category> {
        return walletDataSource.getIncomeCategoriesBlocking()
    }

    fun getWalletBlocking() = walletDataSource.getWalletBlocking().toWallet()

    fun insertIncome(income: Income) {
        val wallet = walletDataSource.getWallet(income.walletId)
        walletDataSource.updateWallet(wallet.copy(balance = wallet.balance + income.income))
        walletDataSource.insertIncome(income)
    }

    @Throws(IllegalArgumentException::class)
    fun insertExpense(expense: Expense) {
        val wallet = walletDataSource.getWallet(expense.walletId)

        if (wallet.balance < expense.expense) {
            throw IllegalArgumentException("Expense cannot be greater than balance")
        }

        walletDataSource.updateWallet(wallet.copy(balance = wallet.balance - expense.expense))
        walletDataSource.insertExpense(expense)
    }

    fun insertCategory(category: Category) {
        walletDataSource.insertCategory(category)
    }

    fun insertCategories(categories: List<Category>) {
        walletDataSource.insertCategories(categories)
    }

    fun getDebts(): Flow<List<Expense>> {
        return walletDataSource.getDebts()
    }

    fun removeDebt(expense: Expense) {
        return walletDataSource.removeDebt(expense)
    }
}