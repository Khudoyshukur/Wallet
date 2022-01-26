package uz.androdev.wallet.ui.util

import android.content.Context
import uz.androdev.wallet.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

fun Double.toFormattedBalance(context: Context): String {
    val format = NumberFormat.getInstance(Locale.US)
    return String.format(context.getString(R.string.currency), format.format(this))
}

fun Date.toFormattedDate(): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM, yyyy hh:mm", Locale("uz"))
    return simpleDateFormat.format(this)
}