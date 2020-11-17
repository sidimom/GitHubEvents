package com.example.events.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.events.databinding.FragmentRepoBinding
import com.example.events.presentation.presenter.RepoPresenter
import com.example.events.presentation.view.RepoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView {

    private val presenter by moxyPresenter { RepoPresenter() }

    private var bindingNull: FragmentRepoBinding? = null
    private val binding
        get() = bindingNull!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNull = FragmentRepoBinding.inflate(inflater, container, false)

        val repoUrl = RepoFragmentArgs.fromBundle(requireArguments()).repoUrl
        presenter.getWebRepo(repoUrl)

        return binding.root
    }

    override fun onDestroyView() {
        bindingNull = null
        super.onDestroyView()
    }

    override fun setRepo(repo: String) {
        binding.webView.loadUrl(repo)
    }

    override fun showLoading() {
        binding.pbCircular.isVisible = true
    }

    override fun hideLoading() {
        binding.pbCircular.isVisible = false
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }
}