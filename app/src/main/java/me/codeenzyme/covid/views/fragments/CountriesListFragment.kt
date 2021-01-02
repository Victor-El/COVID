package me.codeenzyme.covid.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.codeenzyme.covid.R
import me.codeenzyme.covid.databinding.FragmentCountriesListBinding
import me.codeenzyme.covid.models.CountriesCasesResponseItem
import me.codeenzyme.covid.viewmodels.CountriesStatsViewModel
import me.codeenzyme.covid.views.activities.MainActivity
import me.codeenzyme.covid.views.adapters.CountriesStatAdapter
import timber.log.Timber

@AndroidEntryPoint
class CountriesListFragment: Fragment() {

    private lateinit var binding: FragmentCountriesListBinding

    private val countriesStatViewModel by viewModels<CountriesStatsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activityBinding = (activity as MainActivity).getViewBinding()
        activityBinding.searchView.visibility = View.VISIBLE
        activityBinding.bottomNav.selectedItemId = R.id.bottom_nav_list

        binding = FragmentCountriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        val pagingAdapter = CountriesStatAdapter(requireContext())

        binding.countriesRecyclerView.layoutManager = linearLayoutManager
        binding.countriesRecyclerView.adapter = pagingAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            countriesStatViewModel.pagedData.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

}