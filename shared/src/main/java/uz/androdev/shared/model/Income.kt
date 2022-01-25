package uz.androdev.shared.model

import java.util.*

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

data class Income(
    val id: Long? = null,
    val categoryId: Long,
    val details: String,
    val datetime: Date,
    val walletId: Long = 0,
    val income: Double
)