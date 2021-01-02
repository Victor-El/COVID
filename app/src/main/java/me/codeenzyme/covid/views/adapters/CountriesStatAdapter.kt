package me.codeenzyme.covid.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.codeenzyme.covid.R
import me.codeenzyme.covid.Util
import me.codeenzyme.covid.databinding.CountryLayoutBinding
import me.codeenzyme.covid.models.CountriesCasesResponseItem
import javax.inject.Inject

class CountriesStatAdapter(private val context: Context):
    PagingDataAdapter<CountriesCasesResponseItem, CountriesStatAdapter.CountriesStatViewHolder>(
        CountriesComparatorDiffUtil
    ) {

    val util = Util(context)

    private lateinit var _binding: CountryLayoutBinding

    inner class CountriesStatViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: CountriesStatViewHolder, position: Int) {
        val binding = CountryLayoutBinding.bind(holder.itemView)
        binding.run {
            Glide.with(context).load(getItem(position)?.countryInfo?.flag).into(binding.countryFlagView)
            countryName.text = getItem(position)?.country
            casesView.text = context.getString(R.string.cases, util.formatNumber(getItem(position)?.cases))
            todayCasesTextView.text = context.getString(R.string.today_cases, util.formatNumber(getItem(position)?.todayCases))
            activeCasesTextView.text =context.getString(R.string.active_cases, util.formatNumber(getItem(position)?.active))
            deathsTextView.text = context.getString(R.string.deaths, util.formatNumber(getItem(position)?.deaths))
            todayDeathsTextView.text = context.getString(R.string.today_deaths, util.formatNumber(getItem(position)?.todayDeaths))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesStatViewHolder {
        _binding = CountryLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return CountriesStatViewHolder(_binding.root)
    }
}

object CountriesComparatorDiffUtil: DiffUtil.ItemCallback<CountriesCasesResponseItem>() {
    override fun areItemsTheSame(
        oldItem: CountriesCasesResponseItem,
        newItem: CountriesCasesResponseItem
    ): Boolean {
        return oldItem.countryInfo.id == newItem.countryInfo.id
    }

    override fun areContentsTheSame(
        oldItem: CountriesCasesResponseItem,
        newItem: CountriesCasesResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}