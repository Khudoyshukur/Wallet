package uz.androdev.shared.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.androdev.shared.entity.CategoryEntity
import uz.androdev.shared.entity.ExpenseEntity
import uz.androdev.shared.entity.IncomeEntity
import uz.androdev.shared.entity.WalletEntity

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertWallet(walletEntity: WalletEntity): Long

    @Query("select * from WalletEntity limit 1")
    fun getWallet(): Flow<WalletEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateWallet(wallet: WalletEntity)

    @Query("select * from WalletEntity limit 1")
    fun getWalletBlocking(): WalletEntity

    @Query("select * from WalletEntity")
    fun getAllWallets(): List<WalletEntity>

    @Insert
    fun insertCategory(categoryEntity: CategoryEntity)

    @Insert
    fun insertCategories(categoryEntities: List<CategoryEntity>)

    @Delete
    fun deleteCategory(categoryEntity: CategoryEntity)

    @Update
    fun updateCategory(categoryEntity: CategoryEntity)

    @Insert
    fun insertExpense(expenseEntity: ExpenseEntity)

    @Insert
    fun insertIncome(incomeEntity: IncomeEntity)

    @Query("select * from ExpenseEntity where wallet_id=:walletId")
    fun getExpenses(walletId: Long): LiveData<List<ExpenseEntity>>

    @Query("select * from IncomeEntity where wallet_id=:walletId")
    fun getIncomes(walletId: Long): LiveData<List<IncomeEntity>>

    @Query("select * from CategoryEntity where type=:type")
    fun getCategoriesByType(type: String): LiveData<List<CategoryEntity>>

    @Query("select * from CategoryEntity where type=:type")
    fun getCategoriesByTypeBlocking(type: String): List<CategoryEntity>

    @Query("select * from WalletEntity where wallet_id=:walletId")
    fun getWallet(walletId: Long): WalletEntity

    @Query("select * from ExpenseEntity where is_debt")
    fun getDebts(): Flow<List<ExpenseEntity>>

    @Update
    fun updateExpense(expenseEntity: ExpenseEntity)

    @Query("select * from CategoryEntity")
    fun getCategories(): List<CategoryEntity>
}