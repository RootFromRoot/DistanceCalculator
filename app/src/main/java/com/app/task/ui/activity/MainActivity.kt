package com.app.task.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.app.task.R
import com.app.task.data.model.Coordinate
import com.app.task.data.model.Point
import com.app.task.data.model.SearchRequest
import com.app.task.ui.presenter.MainActivityImpl
import com.app.task.ui.view.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {

    override var activity: MainActivity = this
    private val presenter = MainActivityImpl()
    var checkField: Boolean = false
    lateinit var startLatitude: String
    lateinit var startLongitude: String
    lateinit var endLatitude: String
    lateinit var endLongitude: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        presenter.bind(this)
    }

    @SuppressLint("SetTextI18n")
    override fun setupView() {
        setContentView(R.layout.activity_main)

        fab_calculate.setOnClickListener {
            startLatitude = et_start_latitude.text.toString()
            startLongitude = et_start_longitude.text.toString()
            endLatitude = et_end_latitude.text.toString()
            endLongitude = et_end_longitude.text.toString()

            if (et_start_latitude.text.isNotEmpty()
                && et_start_longitude.text.isNotEmpty()
                && et_end_latitude.text.isNotEmpty()
                && et_end_longitude.text.isNotEmpty()
            ) checkField = true

            if (checkField) {
                presenter.calculateDistance()

                presenter.insertRequestToDB(
                    searchRequest = SearchRequest(
                        id = null,
                        startLatitude = startLatitude.toDouble(),
                        startLongitude = startLongitude.toDouble(),
                        endLatitude = endLatitude.toDouble(),
                        endLongitude = endLongitude.toDouble(),
                        distance = presenter.distance
                    )
                )
                tv_result.text = presenter.distance.toString() + " Meters"
            } else Toast.makeText(this, "One of the fields is empty", Toast.LENGTH_SHORT).show()
        }

        fab_history.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}


