package com.app.task.ui.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.app.task.R
import com.app.task.data.model.SearchRequest

import com.app.task.data.repositorygetAll.SearchRequestRepository
import com.app.task.data.util.Application
import com.app.task.ui.adapter.HistoryAdapter
import com.app.task.ui.view.HistoryActivityView
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryActivityView {

    private lateinit var repository: SearchRequestRepository
    private lateinit var adapter: HistoryAdapter
    private var historyRequestsList: ArrayList<SearchRequest> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = SearchRequestRepository((application as Application).db.searchRequestDAO())
        setupView()
    }

    override fun setupView() {
        setContentView(R.layout.activity_history)
        setupAdapter()
        updateAdapterItems()
    }

    override fun setupAdapter() {
        adapter = HistoryAdapter(historyRequestsList)
        rv_history_list.layoutManager = LinearLayoutManager(applicationContext)
        rv_history_list.adapter = adapter
    }

    @SuppressLint("CheckResult")
    override fun updateAdapterItems() {
        repository.getLastTenQueries().subscribe(
            {
                adapter.items = ArrayList(it)
                adapter.notifyDataSetChanged()
            },
            { Log.i("HistoryActivity", it.toString()) }
        )
    }

}
