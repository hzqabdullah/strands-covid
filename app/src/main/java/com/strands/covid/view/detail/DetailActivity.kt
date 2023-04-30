package com.strands.covid.view.detail

import android.annotation.SuppressLint
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.strands.covid.base.BaseActivity
import com.strands.covid.databinding.ActivityDetailBinding
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.general.FailedResult
import com.strands.covid.domain.model.generator.CaseProportionGenerator
import com.strands.covid.domain.model.generator.CountryFlagGenerator
import com.strands.covid.domain.model.generator.DateFormatGenerator
import com.strands.covid.domain.model.generator.DateFormatGenerator.DATE_TIME
import com.strands.covid.domain.model.generator.DateFormatGenerator.ISO8601_MILLIS
import com.strands.covid.domain.model.generator.NumberFormatGenerator.formatNumberWithComma
import com.strands.covid.util.emptyInt
import com.strands.covid.util.loadImageUrl
import com.strands.covid.util.subscribeSingleState
import com.strands.covid.util.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class DetailActivity : BaseActivity() {

    private val binding by viewBinding(ActivityDetailBinding::inflate)
    private val viewModel: DetailViewModel by viewModel()

    override fun getLayoutResource(): Int = emptyInt()
    override fun getLayoutBinding() = binding.root

    private val affectedCountry: AffectedCountry by lazy {
        intent.getSerializableExtra(AFFECTED_COUNTRY) as AffectedCountry
    }

    override fun setupView() {
        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.layoutSwipeRefresh.setOnRefreshListener {
            binding.layoutSwipeRefresh.isRefreshing = false
            viewModel.onEvent(DetailViewModel.Event.OnCreate(affectedCountry = affectedCountry))
        }
        viewModel.onEvent(DetailViewModel.Event.OnCreate(affectedCountry = affectedCountry))
    }

    override fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when (it) {
                is DetailViewModel.State.ShowAffectedCountryBarChart -> showAffectedCountryBarChart(
                    it.barData,
                    it.barChartTitle
                )

                is DetailViewModel.State.ShowAffectedCountryInfo -> showAffectedCountryInfo(it.affectedCountry)
                is DetailViewModel.State.ShowFailed -> showFailed(it.failedResult)
                is DetailViewModel.State.ShowLoading -> showLoadingScreen(it.isLoading)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showAffectedCountryInfo(affectedCountry: AffectedCountry) = with(binding) {
        val imagePath = CountryFlagGenerator.getFlagImagePath(affectedCountry.countryCode)
        binding.ivCountryFlag.loadImageUrl(this@DetailActivity, imagePath)
        binding.tvCountryName.text = affectedCountry.countryName
        binding.tvLastUpdated.text = "Last update: ${
            DateFormatGenerator.convertDate(
                date = affectedCountry.date,
                currentFormat = ISO8601_MILLIS,
                expectedFormat = DATE_TIME
            )
        }"
        binding.tvTotalCase.text = affectedCountry.totalConfirmedCase.formatNumberWithComma()
        binding.tvActiveCase.text = affectedCountry.newConfirmedCase.formatNumberWithComma()
        binding.tvDeathCase.text = affectedCountry.totalDeathCase.formatNumberWithComma()
        binding.tvActiveCasePerHab.text = CaseProportionGenerator.getCasePerHabInString(
            affectedCountry.newConfirmedCase
        )
        binding.tvDeathCasePerHab.text = CaseProportionGenerator.getCasePerHabInString(
            affectedCountry.totalDeathCase
        )
    }

    private fun showAffectedCountryBarChart(
        barData: BarData,
        barChartTitle: List<String>
    ) = with(binding) {
        barChart.data = barData
        val xAxis = barChart.xAxis
        xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(barChartTitle)
            setCenterAxisLabels(true)
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1.0F
            isGranularityEnabled = true
        }
        barData.barWidth = 0.10F
        barChart.apply {
            axisLeft.axisMinimum = 0F
            groupBars(0F, 0.44F, 0.10F)
            invalidate()
        }
    }

    private fun showFailed(failedResult: FailedResult) {
        showMessageDialog(
            title = failedResult.title,
            message = failedResult.description,
            firstButton = "Refresh",
            secondButton = "Dismiss",
            firstButtonClickAction = {
                viewModel.onEvent(DetailViewModel.Event.OnCreate(affectedCountry))
            }
        )
    }

    companion object {
        private const val AFFECTED_COUNTRY = ".affected-country"

        fun withData(affectedCountry: AffectedCountry): Array<Pair<String, *>> {
            return arrayOf(AFFECTED_COUNTRY to affectedCountry)
        }
    }
}
