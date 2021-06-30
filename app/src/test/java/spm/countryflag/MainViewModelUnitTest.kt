package spm.countryflag

import android.content.Context
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import spm.countryflag.ui.MainActivity
import spm.countryflag.viewModel.MainViewModel


/**
 * Created by Sibaprasad Mohanty on 30/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MainViewModelUnitTest {

    @Mock
    lateinit var mainActivity: MainActivity

    @Mock
    lateinit var country: Country

    @Mock
    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {

    }

    @Before

    fun setUpAfter() {

    }

    @Test
    fun countryCodeValidator_correctContryCode_returnTrue() {
        Assert.assertTrue(MainViewModel(context).isValidCountry("ie"))
        Assert.assertTrue(MainViewModel(context).isValidCountry("gh"))
        Assert.assertTrue(MainViewModel(context).isValidCountry("@in"))
    }

    @Test
    fun countryCodeValidator_inCorrectContryCode_returnTrue() {
        Assert.assertFalse(MainViewModel(context).isValidCountry("12"))
        Assert.assertFalse(MainViewModel(context).isValidCountry("d"))
        Assert.assertFalse(MainViewModel(context).isValidCountry("@5"))
    }

    @Test
    fun onSaveButtonClickTest() {
        mainViewModel.insertCountry(country)
        verify(mainViewModel, only()).getSavedCountries()
    }

}