package uz.androdev.shared.model

import java.util.*

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

abstract class Transaction {
    abstract val mId: Long?
    abstract val mDetails: String
    abstract val mDate: Date
    abstract val mAmount: Double
    abstract val mType: TransactionType

    override fun equals(other: Any?): Boolean {
        if (other !is Transaction) {
            return false
        }

        return mId == other.mId &&
                mAmount == other.mAmount &&
                mDetails == other.mDetails &&
                mDate == other.mDate &&
                mType == other.mType
    }
}

enum class TransactionType {
    INCOME, EXPENSE
}