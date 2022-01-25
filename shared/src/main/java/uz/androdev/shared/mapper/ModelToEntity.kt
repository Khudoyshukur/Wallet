package uz.androdev.shared.mapper

import uz.androdev.shared.entity.CategoryEntity
import uz.androdev.shared.entity.ExpenseEntity
import uz.androdev.shared.entity.IncomeEntity
import uz.androdev.shared.entity.WalletEntity
import uz.androdev.shared.model.Category
import uz.androdev.shared.model.Expense
import uz.androdev.shared.model.Income
import uz.androdev.shared.model.Wallet

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

fun Category.toCategoryEntity() = CategoryEntity(
    id = id,
    title = title,
    default = default,
    type = type.toString()
)

fun Expense.toExpenseEntity() = ExpenseEntity(
    id = id,
    categoryId = categoryId,
    details = details,
    datetime = datetime,
    walletId = walletId,
    expense = expense,
    isDebt = isDebt
)

fun Income.toIncomeEntity() = IncomeEntity(
    id = id,
    categoryId = categoryId,
    details = details,
    datetime = datetime,
    walletId = walletId,
    income = income
)

fun Wallet.toWalletEntity() = WalletEntity(
    id = id,
    balance = balance
)