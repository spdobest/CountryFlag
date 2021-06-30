package spm.countryflag.viewmodel

import android.content.Context
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import spm.countryflag.Country
import spm.countryflag.viewModel.MainViewModel


/**
 * Created by Sibaprasad Mohanty on 30/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */
class MainViewModelUnitTest {


    @Mock
    private lateinit var mockApplicationContext: Context

    private lateinit var viewModel: MainViewModel

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(mockApplicationContext)
    }

    @Before

    fun setUpAfter() {

    }

    @Test
    fun countryCodeValidator_correctContryCode_returnTrue() {
        Assert.assertTrue(viewModel.isValidCountry("ie"))
        Assert.assertTrue(viewModel.isValidCountry("gh"))
        Assert.assertTrue(viewModel.isValidCountry("in"))
    }

    @Test
    fun countryCodeValidator_inCorrectContryCode_returnTrue() {
        Assert.assertFalse(viewModel.isValidCountry("12"))
        Assert.assertFalse(viewModel.isValidCountry("&%"))
        Assert.assertFalse(viewModel.isValidCountry("@5"))
    }

    @Test
    fun validCountryList() {

        val countryList = emptyList<Country>()
        Assert.assertFalse(viewModel.isValidCountryList(countryList))

        val country = Country(1, "CN", "https://www.countryflags.io/cn/shiny/64.pn")
        val nonEMptyCountryList = ArrayList<Country>()
        nonEMptyCountryList.add(country)
        Assert.assertTrue(viewModel.isValidCountryList(nonEMptyCountryList))
    }

    @Test
    fun onSaveButtonClickTest() {
        val country = Country(1, "CN", "https://www.countryflags.io/cn/shiny/64.pn")
        viewModel.insertCountry(country)
        verify(viewModel, only()).getSavedCountries()
    }

    @Test
    fun isLiveDataEmitting_observeForever() {
        val country = Country(1, "CN", "https://www.countryflags.io/cn/shiny/64.pn")
        viewModel.insertCountry(country)

        viewModel.observSavedCountries().observeForever { }

        assertEquals(viewModel.getStateOfCountry().value?.data?.get(0)?.countryCode, "CN")
    }
}