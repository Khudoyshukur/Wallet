package uz.androdev.shared.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_id")
    var id: Long? = null,

    @ColumnInfo(name = "category")
    var categoryId: Long = 0,

    @ColumnInfo(name = "details")
    var details: String = "",

    @ColumnInfo(name = "date")
    var datetime: Date = Calendar.getInstance().time,

    @ColumnInfo(name = "wallet_id")
    var walletId: Long = -1,

    @ColumnInfo(name = "expense")
    var expense: Double = 0.0,

    @ColumnInfo(name = "is_debt")
    var isDebt: Boolean = false
)