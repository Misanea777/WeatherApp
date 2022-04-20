package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.databinding.FragmentWeeklyForecastBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklyForecastFragment : Fragment() {

    private lateinit var binding: FragmentWeeklyForecastBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: WeeklyForecastRecycleViewAdapter

    private val viewModel by viewModels<WeeklyForecastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeeklyForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycleView()

        viewModel.getDailyForecast()

        initObservers()
    }

    private fun initObservers() {
        viewModel.forecast.observe(viewLifecycleOwner) { forecast ->
            viewAdapter.updateDataSet(forecast.toTypedArray())
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.statusMessage.observe(viewLifecycleOwner) { statusMessage ->
            statusMessage.getContentIfNotHandled()?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecycleView() {
        viewAdapter = WeeklyForecastRecycleViewAdapter(emptyArray())
        recyclerView = binding.forecastRecycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = viewAdapter
        }
    }
}