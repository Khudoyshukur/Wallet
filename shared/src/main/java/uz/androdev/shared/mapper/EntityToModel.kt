package uz.androdev.shared.mapper

import uz.androdev.shared.entity.*
import uz.androdev.shared.model.*

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

fun CategoryEntity.toCategory() = Category(
    id = id!!,
    title = title,
    type = CategoryType.valueOf(type),
    default = default
)

fun ExpenseEntity.toExpenseEntity() = Expense(
    id = id!!,
    categoryId = categoryId,
    details = details,
    datetime = datetime,
    walletId = walletId,
    expense = expense,
    isDebt = isDebt
)

fun IncomeEntity.toIncome() = Income(
    id = id!!,
    categoryId = categoryId,
    details = details,
    datetime = datetime,
    walletId = walletId,
    income = income
)

fun WalletEntity.toWallet() = Wallet(
    id = id,
    balance = balance
)