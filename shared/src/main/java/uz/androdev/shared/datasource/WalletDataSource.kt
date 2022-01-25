package uz.androdev.shared.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androdev.shared.db.Database
import uz.androdev.shared.entity.IncomeEntity
import uz.androdev.shared.entity.WalletEntity
import uz.androdev.shared.mapper.*
import uz.androdev.shared.model.*
import java.util.*
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class WalletDataSource @Inject constructor(
    private val database: Database
) {
    fun getWallet(): Flow<WalletEntity> {
        return database.walletDao.getWallet()
    }

    fun getExpenseCategories(): LiveData<List<Category>> {
        val categories = database.walletDao.getCategoriesByType(CategoryType.EXPENSE.toString())

        return Transformations.map(categories) { list ->
            list.map { it.toCategory() }
        }
    }

    fun getIncomeCategories(): LiveData<List<Category>> {
        val categories = database.walletDao.getCategoriesByType(CategoryType.INCOME.toString())

        return Transformations.map(categories) { list ->
            list.map { it.toCategory() }
        }
    }

    fun getWallet(walletId: Long): Wallet {
        val wallet = database.walletDao.getWallet(walletId)

        return wallet.toWallet()
    }

    fun insertExpense(expense: Expense) {
        database.walletDao.insertExpense(expense.toExpenseEntity())
    }

    fun insertCategory(category: Category) {
        database.walletDao.insertCategory(category.toCategoryEntity())
    }

    fun insertIncome(income: Income) {
        database.walletDao.insertIncome(income.toIncomeEntity())
    }

    fun getWalletBlocking() = database.walletDao.getWalletBlocking()

    fun getExpenseCategoriesBlocking(): List<Category> {
        val categories = arrayListOf<Category>()
        categories.addAll(
            database.walletDao.getCategoriesByTypeBlocking(CategoryType.EXPENSE.toString())
                .map { it.toCategory() }
        )
        categories.addAll(getBaseCategories())

        return categories
    }

    fun getIncomeCategoriesBlocking(): List<Category> {
        val categories = arrayListOf<Category>()
        categories.addAll(
            database.walletDao.getCategoriesByTypeBlocking(CategoryType.INCOME.toString())
                .map { it.toCategory() }
        )
        categories.addAll(getBaseCategories())

        return categories
    }


    private fun getBaseCategories() =
        database.walletDao.getCategoriesByTypeBlocking(CategoryType.BASE.toString())
            .map { it.toCategory() }

    fun insertCategories(categories: List<Category>) {
        database.walletDao.insertCategories(categoryEntities = categories.map { it.toCategoryEntity() })
    }

    fun updateWallet(wallet: Wallet) {
        database.walletDao.updateWallet(wallet.toWalletEntity())
    }

    fun getDebts(): Flow<List<Expense>> {
        return database.walletDao.getDebts().map { it.map { it.toExpenseEntity() } }
    }

    fun removeDebt(expense: Expense) = with(database.walletDao) {
        updateExpense(expense.copy(isDebt = false).toExpenseEntity())
        insertIncome(
            IncomeEntity(
                categoryId = expense.categoryId,
                details = "${expense.details}\nQarz qaytarib olindi",
                walletId = expense.walletId,
                income = expense.expense,
                datetime = Calendar.getInstance().time
            )
        )
        val wallet = getWallet(expense.walletId)
        updateWallet(wallet.copy(balance = wallet.balance + expense.expense))
    }
}