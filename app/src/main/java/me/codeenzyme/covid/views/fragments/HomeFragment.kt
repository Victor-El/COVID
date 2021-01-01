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
import me.codeenzyme.covid.databinding.FragmentHomeBinding
import me.codeenzyme.covid.viewmodels.GlobalStatViewModel
import timber.log.Timber
import java.text.NumberFormat

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val globalStatViewModel by viewModels<GlobalStatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

            globalStatViewModel.getGlobalStat().collect {

                binding.run {
                    homeSwipeRefresh.isRefreshing = false
                    affectedCountriesView.text = formatNumber(it.affectedCountries)
                    totalPopulationView.text = formatNumber(it.population)
                    totalCasesView.text = formatNumber(it.cases)
                    todayCasesView.text = formatNumber(it.todayCases)
                    activeCasesView.text = formatNumber(it.active)
                    activePerMillionView.text = formatNumber(it.activePerOneMillion)
                    casesPerMillionView.text = formatNumber(it.casesPerOneMillion)
                    recoveredView.text = formatNumber(it.recovered)
                    recoveredTodayView.text = formatNumber(it.todayRecovered)
                    recoveryPerMillion.text = formatNumber(it.recoveredPerOneMillion)
                    criticalCasesView.text = formatNumber(it.critical)
                    criticalPerMillionView.text = formatNumber(it.criticalPerOneMillion)
                    totalDeathsView.text = formatNumber(it.deaths)
                    todayDeathView.text = formatNumber(it.todayDeaths)
                    deathsPerMillionView.text = formatNumber(it.deathsPerOneMillion)
                    totalTestsView.text = formatNumber(it.tests)
                    testsPerMillionView.text = formatNumber(it.testsPerOneMillion)
                }
            }
        }
    }

    private fun formatNumber(num: Number): String {
        return NumberFormat.getIntegerInstance().format(num)
    }

}