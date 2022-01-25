package uz.androdev.shared.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.androdev.shared.db.DATABASE_NAME
import uz.androdev.shared.db.Database
import uz.androdev.shared.entity.WalletEntity
import uz.androdev.shared.mapper.toCategoryEntity
import uz.androdev.shared.model.Category
import uz.androdev.shared.model.CategoryType
import javax.inject.Singleton

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
            .build().also {
                CoroutineScope(Dispatchers.IO).launch {
                    if (it.walletDao.getAllWallets().isEmpty()) {
                        it.walletDao.insertWallet(WalletEntity())
                    }

                    if (it.walletDao.getCategories().isEmpty()) {
                        it.walletDao.insertCategories(getDefaultCategories().map { it.toCategoryEntity() })
                    }
                }
            }

    private fun getDefaultCategories() = listOf(
        Category(title = "Oziq-ovqat", default = true, type = CategoryType.EXPENSE),
        Category(title = "Transport", default = true, type = CategoryType.EXPENSE),
        Category(title = "Kiyim-kechak", default = true, type = CategoryType.EXPENSE),
        Category(title = "Ta'lim", default = true, type = CategoryType.EXPENSE),

        Category(title = "Stipendiya", default = true, type = CategoryType.INCOME),
        Category(title = "Maosh", default = true, type = CategoryType.INCOME),

        Category(title = "Qarz", default = true, type = CategoryType.BASE)
    )
}