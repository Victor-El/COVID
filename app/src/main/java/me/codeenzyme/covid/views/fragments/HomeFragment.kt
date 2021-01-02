package me.codeenzyme.covid.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import me.codeenzyme.covid.R
import me.codeenzyme.covid.Util
import me.codeenzyme.covid.data.local.entities.GlobalStat
import me.codeenzyme.covid.databinding.FragmentHomeBinding
import me.codeenzyme.covid.viewmodels.GlobalStatViewModel
import me.codeenzyme.covid.views.activities.MainActivity
import timber.log.Timber
import java.text.NumberFormat
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var util: Util

    private lateinit var binding: FragmentHomeBinding

    private val globalStatViewModel by viewModels<GlobalStatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activityBinding = (activity as MainActivity).getViewBinding()
        activityBinding.searchView.visibility = View.GONE
        activityBinding.bottomNav.selectedItemId = R.id.bottom_nav_home

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeSwipeRefresh.setOnRefreshListener {
            loadData()
        }

        loadData()
    }

    private fun loadData() {
        lifecycleScope.launchWhenCreated {
            binding.homeSwipeRefresh.isRefreshing = true

            globalStatViewModel.getGlobalStat().collect {it: GlobalStat? ->

                binding.run {
                    homeSwipeRefresh.isRefreshing = false
                    affectedCountriesView.text = util.formatNumber(it?.affectedCountries)
                    totalPopulationView.text = util.formatNumber(it?.population)
                    totalCasesView.text = util.formatNumber(it?.cases)
                    todayCasesView.text = util.formatNumber(it?.todayCases)
                    activeCasesView.text = util.formatNumber(it?.active)
                    activePerMillionView.text = util.formatNumber(it?.activePerOneMillion)
                    casesPerMillionView.text = util.formatNumber(it?.casesPerOneMillion)
                    recoveredView.text = util.formatNumber(it?.recovered)
                    recoveredTodayView.text = util.formatNumber(it?.todayRecovered)
                    recoveryPerMillion.text = util.formatNumber(it?.recoveredPerOneMillion)
                    criticalCasesView.text = util.formatNumber(it?.critical)
                    criticalPerMillionView.text = util.formatNumber(it?.criticalPerOneMillion)
                    totalDeathsView.text = util.formatNumber(it?.deaths)
                    todayDeathView.text = util.formatNumber(it?.todayDeaths)
                    deathsPerMillionView.text = util.formatNumber(it?.deathsPerOneMillion)
                    totalTestsView.text = util.formatNumber(it?.tests)
                    testsPerMillionView.text = util.formatNumber(it?.testsPerOneMillion)
                }
            }
        }
    }

}