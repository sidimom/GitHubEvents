package com.example.events.presentation.presenter

import com.example.events.api.NetworkService
import com.example.events.presentation.view.EventsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class EventsListPresenter: MvpPresenter<EventsListView>() {

    private val disposables = CompositeDisposable()

    fun viewIsReady() {
        viewState.showLoading()
        getEvents()
    }

    fun refresh() {
        viewState.refreshEvents()
        getEvents()
    }

    private fun getEvents() {
        disposables.add(NetworkService.getInstance().api
            .getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { viewState.hideLoading() }
            .subscribe({ viewState.setEvents(it) },
                { viewState.showError(it.toString()) }))
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}