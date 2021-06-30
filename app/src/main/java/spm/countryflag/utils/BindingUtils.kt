package spm.countryflag.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import spm.countryflag.ui.adapter.CountryAdapter


/**
 * Created by Sibaprasad Mohanty on 30/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class BindingUtils {

    companion object {
        @JvmStatic
        @BindingAdapter("flagImage")
        fun loadImage(flagImageView: AppCompatImageView, imageUrl: String) {
            Picasso
                .get()
                .load(imageUrl)
                .into(flagImageView)

        }

        @JvmStatic
        @BindingAdapter("adapter")
        fun setRecyclerViewAdapter(recyclerViewCountry: RecyclerView, adapter: CountryAdapter) {
            recyclerViewCountry.adapter = adapter
            recyclerViewCountry.layoutManager = LinearLayoutManager(recyclerViewCountry.context)
            recyclerViewCountry.addItemDecoration(
                DividerItemDecoration(
                    recyclerViewCountry.context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }
}