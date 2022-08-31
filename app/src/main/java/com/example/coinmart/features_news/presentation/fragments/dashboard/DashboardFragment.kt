package com.example.coinmart.features_news.presentation.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.coinmart.databinding.FragmentDashboardBinding
import com.example.coinmart.features_news.data.remote.response.CurrentPriceResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by activityViewModels()
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.flow.onEach { response ->
            when(response) {
                is DashboardViewModel.STATE.SUCCESS -> {
                    setupData(response.data)
                }
                is DashboardViewModel.STATE.FAILURE -> {

                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setupData(data: CurrentPriceResponse) {
        binding.apply {
            shimmer.visibility = View.GONE
            //ksldjsklajdlk
            container.visibility = View.VISIBLE
            tvChartName.text = data.chartName
            tvTime.text = data.time.updated
            tvPrice.text = "${data.bpi.USD.rate} ${data.bpi.USD.code}"
            tvDisclaimer.text = data.disclaimer
        }
    }
}