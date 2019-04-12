package com.app.task.ui.presenter

import android.util.Log
import com.app.task.data.model.Coordinate
import com.app.task.data.model.Point
import com.app.task.data.model.SearchRequest
import com.app.task.data.repositorygetAll.SearchRequestRepository
import com.app.task.data.util.Application
import com.app.task.data.util.DistanceUtil
import com.app.task.ui.view.MainActivityView

interface MainActivityPresenter {
    var view: MainActivityView
    var repository: SearchRequestRepository
    var distance: Double
    fun bind(view: MainActivityView)
    fun calculateDistance()
    fun insertRequestToDB(searchRequest: SearchRequest)

}

class MainActivityImpl : MainActivityPresenter {
    override lateinit var view: MainActivityView
    override var distance: Double = 0.0
    override lateinit var repository: SearchRequestRepository

    override fun calculateDistance() {
        distance = DistanceUtil().vincentyDistance(
            Point(
                Coordinate.fromDegrees(view.activity.startLatitude.toDouble()),
                Coordinate.fromDegrees(view.activity.startLongitude.toDouble())
            ),
            Point(
                Coordinate.fromDegrees(view.activity.endLatitude.toDouble()),
                Coordinate.fromDegrees(view.activity.endLongitude.toDouble())
            )
        )

    }

    override fun insertRequestToDB(searchRequest: SearchRequest) {
        repository.insert(searchRequest).subscribe()
    }

    override fun bind(view: MainActivityView) {
        this.view = view
        repository = SearchRequestRepository((view.activity.application as Application).db.searchRequestDAO())
    }
}