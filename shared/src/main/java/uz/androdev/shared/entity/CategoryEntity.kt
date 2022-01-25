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
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    var id: Long? = null,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "default")
    var default: Boolean = false,

    @ColumnInfo(name = "type")
    var type: String = ""
)