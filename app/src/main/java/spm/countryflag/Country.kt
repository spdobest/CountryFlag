package spm.countryflag

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Created by Sibaprasad Mohanty on 29/06/21.
 * sp.dobest@gmail.com
 */

@Entity(indices = [Index(value = ["countryCode", "countryImage"], unique = true)])
data class Country(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "countryCode") val countryCode: String,
    @ColumnInfo(name = "countryImage") val countryImage: String
)
