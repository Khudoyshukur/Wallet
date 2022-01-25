package uz.androdev.shared.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Entity
data class WalletEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "wallet_id")
    var id: Long? = 0,

    @ColumnInfo(name = "balance")
    var balance: Double = 0.0
)