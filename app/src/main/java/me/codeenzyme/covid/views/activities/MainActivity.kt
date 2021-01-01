package me.codeenzyme.covid.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import me.codeenzyme.covid.NavGraphDirections
import me.codeenzyme.covid.R
import me.codeenzyme.covid.databinding.ActivityMainBinding
import me.codeenzyme.covid.viewmodels.GlobalStatViewModel
import me.codeenzyme.covid.views.fragments.CountriesListFragment
import me.codeenzyme.covid.views.fragments.CountriesListFragmentDirections
import me.codeenzyme.covid.views.fragments.HomeFragmentDirections
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.countriesListFragment))
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        setSupportActionBar(binding.toolbar)

        binding.bottomNav.setOnNavigationItemSelectedListener {

            if (binding.bottomNav.selectedItemId == it.itemId) {
                return@setOnNavigationItemSelectedListener true
            }

            when (it.itemId) {

                R.id.bottom_nav_home -> {
                    binding.searchView.visibility = View.GONE
                    val action = NavGraphDirections.actionGlobalHomeFragment()
                    navController.navigate(action)
                }

                R.id.bottom_nav_list -> {
                    binding.searchView.visibility = View.VISIBLE
                    val action = NavGraphDirections.actionGlobalCountriesListFragment()
                    navController.navigate(action)
                }
            }
            true
        }

        binding.searchView.run {
            visibility = View.GONE
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}