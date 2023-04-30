package com.strands.covid.view.main

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.strands.covid.R
import com.strands.covid.base.BaseActivity
import com.strands.covid.databinding.ActivityMainBinding
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.general.FailedResult
import com.strands.covid.enums.CountrySortByType.*
import com.strands.covid.util.*
import com.strands.covid.view.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModel()

    override fun getLayoutResource(): Int = emptyInt()
    override fun getLayoutBinding() = binding.root

    override fun setupView() {
        viewModel.onEvent(MainViewModel.Event.OnCreate)
        binding.layoutSwipeRefresh.setOnRefreshListener {
            binding.layoutSwipeRefresh.isRefreshing = false
            viewModel.onEvent(MainViewModel.Event.OnCreate)
        }
        binding.etSearch.doAfterTextChanged {
            viewModel.onEvent(MainViewModel.Event.OnSearchCountry(it.toString()))
        }
        binding.ivSort.setOnClickListener {
            showCountrySort(it)
        }
    }

    override fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when (it) {
                is MainViewModel.State.ShowCountryList -> showCountryList(it.countries)
                is MainViewModel.State.ShowEmptyCountryList -> showEmptyCountryList(it.isEmpty)
                is MainViewModel.State.ShowFailed -> showFailed(it.failedResult)
                is MainViewModel.State.ShowLoading -> showLoadingScreen(it.isLoading)
            }
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun showCountrySort(view: View) {
        val popMenu = PopupMenu(this, view, Gravity.RIGHT)
        popMenu.inflate(R.menu.menu_country_sort)
        onClickCountrySortMenuItem(popMenu)
        popMenu.dismiss()
        popMenu.show()
    }

    private fun onClickCountrySortMenuItem(popupMenu: PopupMenu) {
        popupMenu.setOnMenuItemClickListener {
            val sortByType = when (it.itemId) {
                R.id.menu_show_all -> ALL
                R.id.menu_active_case -> ACTIVE
                R.id.menu_death -> DEATH
                R.id.menu_active_case_per_hab -> ACTIVE_PER_HAB
                R.id.menu_death_case_per_hab -> DEATH_PER_HAB
                else -> ALL
            }
            viewModel.onEvent(MainViewModel.Event.OnSortCountryByType(sortByType))
            return@setOnMenuItemClickListener true
        }
    }

    private fun showCountryList(countries: List<AffectedCountry>) {
        val mainAdapter = MainAdapter(this)
        binding.rvCountry.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        mainAdapter.apply {
            countryList = countries.toMutableList()
            onClickListener {
                val data = DetailActivity.withData(affectedCountry = it)
                startActivity<DetailActivity>(*data)
            }
        }
    }

    private fun showEmptyCountryList(isEmpty: Boolean) = with(binding) {
        rvCountry.isVisible = !isEmpty
        layoutEmptyCountry.isVisible = isEmpty
    }

    private fun showFailed(failedResult: FailedResult) {
        showMessageDialog(
            title = failedResult.title,
            message = failedResult.description,
            firstButton = "Refresh",
            secondButton = "Dismiss",
            firstButtonClickAction = { viewModel.onEvent(MainViewModel.Event.OnCreate) }
        )
    }
}
