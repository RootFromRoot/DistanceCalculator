package com.app.task.data.model

class Point (latitude: Coordinate, longitude: Coordinate) {

    var latitude:Double = 0.toDouble()
    var longitude:Double = 0.toDouble()

    init{
        this.latitude = latitude.degrees()
        this.longitude = longitude.degrees()
    }

    companion object {
             fun at(latitude: Coordinate, longitude: Coordinate): Point {
            return Point(latitude, longitude)
        }
    }
}