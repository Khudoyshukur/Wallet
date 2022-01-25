package uz.androdev.wallet.base

import uz.androdev.shared.model.Category
import uz.androdev.shared.model.CategoryType

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

fun getDefaultCategories() = listOf(
    Category(title = "Oziq-ovqat", default = true, type = CategoryType.EXPENSE),
    Category(title = "Transport", default = true, type = CategoryType.EXPENSE),
    Category(title = "Kiyim-kechak", default = true, type = CategoryType.EXPENSE),
    Category(title = "Ta'lim", default = true, type = CategoryType.EXPENSE),

    Category(title = "Stipendiya", default = true, type = CategoryType.INCOME),
    Category(title = "Maosh", default = true, type = CategoryType.INCOME),

    Category(title = "Qarz", default = true, type = CategoryType.BASE)
)