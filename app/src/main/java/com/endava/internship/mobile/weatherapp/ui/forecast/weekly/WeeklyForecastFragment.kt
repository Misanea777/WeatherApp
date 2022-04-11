package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.databinding.FragmentWeeklyForecastBinding
import com.endava.internship.mobile.weatherapp.utils.Resource
import java.time.DayOfWeek
import java.time.Month

class WeeklyForecastFragment : Fragment() {

    private lateinit var binding: FragmentWeeklyForecastBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: WeeklyForecastRecycleViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    private val viewModel by viewModels<WeeklyForecastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeeklyForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycleView()

        viewModel.getForecastForNextFiveDays()

        viewModel.forecast.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success ->
                    viewAdapter.updateDataSet(it.value.toTypedArray())
            }
        })

    }

    fun initRecycleView() {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = WeeklyForecastRecycleViewAdapter(emptyArray<Daily>())
        recyclerView = binding.forecastRecycleView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }
}