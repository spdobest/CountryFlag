package spm.countryflag.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import spm.countryflag.Country
import spm.countryflag.database.AppDatabase
import spm.countryflag.database.DatabaseClient
import spm.countryflag.utils.Resource

/**
 * Created by Sibaprasad Mohanty on 29/06/21.
 * sp.dobest@gmail.com
 */

class MainViewModel(context: Context) : ViewModel() {

    private val savedCountries = MutableLiveData<Resource<List<Country>>>()

    private val database: AppDatabase by lazy {
        DatabaseClient.getInstance(context)?.getAppDatabase()!!
    }

    fun insertCountry(country: Country) {
        if (isValidCountry(country.countryCode)) {
            CoroutineScope(Dispatchers.IO).launch {
                database.countryDao().insertCountry(country)
                delay(1000)
                getSavedCountries()
            }
        }
    }

    fun getSavedCountries() {
        viewModelScope.launch {
            savedCountries.postValue(Resource.loading(null))
            try {
                val countriesFromDb = database.countryDao().getAllSavedCountries()
                if (isValidCountryList(countriesFromDb)) {
                    savedCountries.postValue(Resource.success(countriesFromDb))
                } else {
                    savedCountries.postValue(Resource.error("No Data found", emptyList()))
                }
            } catch (e: Exception) {
                savedCountries.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }

    fun getStateOfCountry(): LiveData<Resource<List<Country>>> {
        return savedCountries
    }

    fun observSavedCountries(): LiveData<Resource<List<Country>>> {
        return savedCountries
    }

    fun isValidCountry(countryCode: String?): Boolean {
        return ((!countryCode.equals(""))
                && (countryCode != null)
                && (countryCode.matches("^[a-zA-Z]*$".toRegex())))
    }

    fun isValidCountryList(listCountry: List<Country>) =
        listCountry.isNotEmpty()

}