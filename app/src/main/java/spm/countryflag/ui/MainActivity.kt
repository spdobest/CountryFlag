package spm.countryflag.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import spm.countryflag.Country
import spm.countryflag.R
import spm.countryflag.databinding.ActivityMainBinding
import spm.countryflag.ui.adapter.CountryAdapter
import spm.countryflag.utils.NetworkUtil
import spm.countryflag.utils.Status
import spm.countryflag.viewModel.MainViewModel

/**
 * Created by Sibaprasad Mohanty on 29/06/21.
 * sp.dobest@gmail.com
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var listOfSavedCountries = ArrayList<Country>()

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel(this)
    }

    val adapter: CountryAdapter by lazy {
        CountryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.countryAdapter = adapter
        initializeLayout()
        setObserver()
    }

    private fun loadImage(countryCode: String, save: Boolean = false) {

        if (NetworkUtil.isAvailable(this)) {
            setButtonState(true)
            val countryImageUrl = getString(R.string.imageUrl, countryCode)
            progressLoading.visibility = View.VISIBLE

            Picasso.get()
                .load(countryImageUrl)
                .into(imageViewFlag, object : Callback {
                    override fun onSuccess() {
                        progressLoading.visibility = View.GONE
                        if (save) {
                            val country = Country(
                                id = 0,
                                countryCode = countryCode,
                                countryImage = countryImageUrl
                            )
                            insertCountry(country)
                        }
                    }

                    override fun onError(e: Exception?) {
                        progressLoading.visibility = View.GONE
                        showDialog(e?.message!!)
                        setButtonState(false)
                        imageViewFlag.setImageDrawable(null)
                    }
                })
        } else {
            showDialog(getString(R.string.no_internet))
        }
    }

    fun insertCountry(country: Country) {
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.insertCountry(
                country
            )
        }
    }


    private fun showDialog(message: String) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(getString(R.string.error))
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, _ ->
            if (message.contains("404")) {
                etCountryCode.setText(getString(R.string.empty_string))
            }
            dialog.dismiss()
        }
        alertDialog.show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSave -> {
                val countryCode = etCountryCode.text.toString()
                loadImage(countryCode, true)
                etCountryCode.setText(getString(R.string.empty_string))
            }
        }
    }

    private fun setObserver() {

        mainViewModel.getSavedCountries()

        mainViewModel.observSavedCountries().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressLoadCountries.visibility = View.GONE
                    it.data?.let { countries ->
                        adapter.setCountryList(countries)
                    }
                    recyclerViewCountry.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressLoadCountries.visibility = View.VISIBLE
                    recyclerViewCountry.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressLoadCountries.visibility = View.GONE
                    it.message?.let { it1 -> showDialog(it1) }
                }
            }
        })
    }

    private fun setButtonState(isActive: Boolean) {
        buttonSave.background = if (isActive) ContextCompat.getDrawable(
            this,
            R.drawable.rounded_button
        ) else ContextCompat.getDrawable(this, R.drawable.rounded_button_disable)
        buttonSave.isClickable = isActive
    }

    private fun initializeLayout() {

        buttonSave.setOnClickListener(this)

        etCountryCode.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
                val lentgh = etCountryCode.text?.length ?: 0
                if (lentgh >= 2) {
                    loadImage(countryCode = etCountryCode.text.toString())
                }
                if (lentgh < 2) {
                    imageViewFlag.setImageDrawable(null)
                    setButtonState(false)
                }
            }
        })
    }

}