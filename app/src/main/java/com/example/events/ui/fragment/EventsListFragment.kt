package com.example.events.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.events.R
import com.example.events.api.model.EventItem
import com.example.events.databinding.FragmentEventsListBinding
import com.example.events.presentation.presenter.EventsListPresenter
import com.example.events.presentation.view.EventsListView
import com.example.events.ui.adapter.EventsAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class EventsListFragment : MvpAppCompatFragment(), EventsListView {

    private val presenter by moxyPresenter { EventsListPresenter() }

    private var bindingNull: FragmentEventsListBinding? = null
    private val binding
        get() = bindingNull!!
    private var adapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNull = FragmentEventsListBinding.inflate(inflater, container, false)

        binding.swipeContainer.setColorSchemeResources(R.color.colorPrimary)
        binding.swipeContainer.setOnRefreshListener { presenter.refresh() }

        binding.rvEvents.adapter = adapter
        adapter.listener = { eventItem ->
            NavHostFragment
                .findNavController(this)
                .navigate(
                    EventsListFragmentDirections.actionEventsListFragmentToRepoFragment(eventItem.repo.url)
                )
        }

        if (adapter.itemCount == 0)
            presenter.viewIsReady()

        return binding.root
    }

    override fun onDestroyView() {
        bindingNull = null
        super.onDestroyView()
    }

    override fun setEvents(items: List<EventItem>) {
        adapter.setData(items)
    }

    override fun showLoading() {
        binding.pbCircular.visibility = View.VISIBLE
        binding.swipeContainer.isEnabled = false
    }

    override fun refreshEvents() {
        binding.swipeContainer.isRefreshing = true
    }

    override fun hideLoading() {
        if (binding.swipeContainer.isRefreshing) {
            binding.swipeContainer.isRefreshing = false
        }
        binding.pbCircular.visibility = View.GONE
        binding.swipeContainer.isEnabled = true
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }
}