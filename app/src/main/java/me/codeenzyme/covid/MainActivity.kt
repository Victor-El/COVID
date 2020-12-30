package me.codeenzyme.covid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import me.codeenzyme.covid.viewmodels.GlobalStatViewModel
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val globalStatViewModel by viewModels<GlobalStatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenCreated {
            globalStatViewModel.getGlobalStat().collect {
                Timber.d("${it.todayRecovered}")
            }
        }
    }
}