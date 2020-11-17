package com.example.events.presentation.presenter

import com.example.events.api.NetworkService
import com.example.events.presentation.view.RepoView
import com.example.events.utils.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class RepoPresenter: MvpPresenter<RepoView>() {

    private val disposables = CompositeDisposable()

    fun getWebRepo(repoUrl: String) {
        val repoPath = repoUrl.replace(Const.BASE_URL, "")

        disposables.add(NetworkService.getInstance().api
            .getRepo(repoPath)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { viewState.hideLoading() }
            .subscribe({ viewState.setRepo(it.htmlUrl) },
                { viewState.showError(it.toString()) }))
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}