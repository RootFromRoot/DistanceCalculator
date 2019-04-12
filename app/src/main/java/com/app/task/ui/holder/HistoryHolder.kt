package com.app.task.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
    val startLatitude = view.tv_start_latitude!!
    val startLongitude = view.tv_start_longitude!!
    val endLatitude = view.tv_end_latitude!!
    val endLongitude = view.tv_end_longitude!!
    val distance = view.tv_distance!!
}