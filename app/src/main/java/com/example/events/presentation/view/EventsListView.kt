package com.example.events.presentation.view

import com.example.events.api.model.EventItem
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface EventsListView: MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setEvents(items: List<EventItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun refreshEvents()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(error: String)
}