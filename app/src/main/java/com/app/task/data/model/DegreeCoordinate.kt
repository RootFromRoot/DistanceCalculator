package com.app.task.data.model

class DegreeCoordinate(private val decimalDegrees: Double) : Coordinate() {

    override fun degrees(): Double {
        return decimalDegrees
    }
}