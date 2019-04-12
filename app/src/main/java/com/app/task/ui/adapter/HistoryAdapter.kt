package com.app.task.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.task.R
import com.app.task.data.model.SearchRequest
import com.app.task.ui.holder.HistoryHolder

class HistoryAdapter(var items: ArrayList<SearchRequest>) : RecyclerView.Adapter<HistoryHolder>() {
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        return HistoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false))
    }

    fun setRequest(songs: ArrayList<SearchRequest>) {
        this.items = songs
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.startLatitude?.text = items[position].startLatitude.toString()
        holder.startLongitude?.text = items[position].startLongitude.toString()
        holder.endLatitude?.text = items[position].endLatitude.toString()
        holder.endLongitude?.text = items[position].endLongitude.toString()
        holder.distance?.text = items[position].distance.toString()

    }

    override fun getItemCount(): Int {
        return items.size
    }
}