package spm.countryflag.database

import androidx.room.*
import androidx.room.util.TableInfo
import spm.countryflag.Country


/**
 * Created by Sibaprasad Mohanty on 29/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCountry( countries: Country)

    @Delete
    suspend fun deleteCountry(country: Country)

    @Update
    suspend fun updateCountry(country: Country)

    @Query("SELECT * from country")
    suspend fun getAllSavedCountries(): List<Country>
}