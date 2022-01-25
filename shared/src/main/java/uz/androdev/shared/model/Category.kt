package uz.androdev.shared.model

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

data class Category(
    val id: Long? = null,
    val title: String,
    val default: Boolean = false,
    val type: CategoryType
)

enum class CategoryType {
    INCOME, EXPENSE, BASE
}