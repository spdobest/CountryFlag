package spm.countryflag.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import spm.countryflag.BR
import spm.countryflag.Country
import spm.countryflag.databinding.ItemviewCountryBinding


/**
 * Created by Sibaprasad Mohanty on 29/06/21.
 * sp.dobest@gmail.com
 */

class CountryAdapter() :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {
    private var allCountryList: List<Country> = listOf<Country>()
    private var filteredCountries: List<Country> = listOf<Country>()

    class CountryViewHolder(private val binding: ItemviewCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Country) {
            binding.setVariable(BR.country, obj)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val countryBinding: ItemviewCountryBinding =
            ItemviewCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(countryBinding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    private val COUNTRY_DIFF_CALLBACK: DiffUtil.ItemCallback<Country> =
        object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(
                @NonNull oldCountry: Country,
                @NonNull newCountry: Country
            ): Boolean {
                return oldCountry.countryCode == newCountry.countryCode
            }

            override fun areContentsTheSame(
                @NonNull oldCountry: Country,
                @NonNull newCountry: Country
            ): Boolean {
                return oldCountry.countryImage == newCountry.countryImage
            }
        }

    private val differ: AsyncListDiffer<Country> =
        AsyncListDiffer<Country>(this, COUNTRY_DIFF_CALLBACK)

    fun setCountryList(countries: List<Country>) {
        allCountryList = countries
        differ.submitList(countries)
    }

    private fun filterCountryList(countries: List<Country>) {
        differ.submitList(countries)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val query = charSequence.toString()
                var filtered: List<Country> = ArrayList()
                filtered = if (query.isEmpty()) {
                    allCountryList
                } else {
                    allCountryList.filter {
                        it.countryCode.lowercase().contains(query.lowercase())
                    }
                }
                val results = FilterResults()
                results.count = filtered.size
                results.values = filtered
                return results
            }

            override fun publishResults(charSequence: CharSequence, results: FilterResults) {
                filteredCountries = results.values as ArrayList<Country>
                filterCountryList(filteredCountries)
            }
        }
    }
}