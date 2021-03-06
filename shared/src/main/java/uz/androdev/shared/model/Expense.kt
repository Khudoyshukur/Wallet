package uz.androdev.shared.model

import java.util.*

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

data class Expense(
    val id: Long? = null,
    val categoryId: Long,
    val details: String,
    val datetime: Date,
    val walletId: Long = 0,
    val expense: Double,
    val isDebt: Boolean = false
) : Transaction() {
    override val mId: Long?
        get() = id
    override val mDetails: String
        get() = details
    override val mDate: Date
        get() = datetime
    override val mAmount: Double
        get() = expense
    override val mType: TransactionType
        get() = TransactionType.EXPENSE
}