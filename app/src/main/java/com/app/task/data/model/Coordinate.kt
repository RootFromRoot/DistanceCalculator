package com.app.task.data.model

abstract class Coordinate {

    abstract fun degrees():Double

    companion object {
        fun fromDegrees(decimalDegrees: Double): DegreeCoordinate {
            return DegreeCoordinate(decimalDegrees)
        }
    }
}