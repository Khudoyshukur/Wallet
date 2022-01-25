package uz.androdev.shared.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.androdev.shared.entity.CategoryEntity
import uz.androdev.shared.entity.ExpenseEntity
import uz.androdev.shared.entity.IncomeEntity
import uz.androdev.shared.entity.WalletEntity
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

const val DATABASE_NAME = "WalletDatabase.db"
private const val DATABASE_VERSION = 1

@androidx.room.Database(
    entities = [CategoryEntity::class, ExpenseEntity::class, IncomeEntity::class, WalletEntity::class],
    version = DATABASE_VERSION
)
@TypeConverters(MyTypeConverters::class)
abstract class Database : RoomDatabase() {
    abstract val walletDao: WalletDao
}