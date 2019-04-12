package com.app.task.data.util

import com.app.task.data.model.Point
import java.lang.Math.*

//href="http://www.movable-type.co.uk/scripts/latlong.html
class DistanceUtil {

    private fun vincenty(standPoint: Point, forePoint: Point): Vincenty {
        val alpha1 = toRadians(standPoint.longitude)
        val alpha2 = toRadians(forePoint.longitude)
        val fi1 = toRadians(standPoint.latitude)
        val fi2 = toRadians(forePoint.latitude)
        val a = 6378137.0
        val b = 6_356_752.314245
        val f = 1 / 298.257223563
        val L = alpha2 - alpha1
        val tanU1 = (1 - f) * tan(fi1)
        val cosU1 = 1 / sqrt((1 + tanU1 * tanU1))
        val sinU1 = tanU1 * cosU1
        val tanU2 = (1 - f) * tan(fi2)
        val cosU2 = 1 / sqrt((1 + tanU2 * tanU2))
        val sinU2 = tanU2 * cosU2

        var alpha = L
        var alphaʹ: Double
        var iterationLimit = 100.0
        var cosSqAlpha: Double
        var sigma: Double
        var cos2sigmaM: Double
        var cossigma: Double
        var sinsigma: Double
        var sinalpha: Double
        var cosalpha: Double

        do {
            sinalpha = sin(alpha)
            cosalpha = cos(alpha)
            val sinSqsigma = (cosU2 * sinalpha) * (cosU2 * sinalpha) +
                    (cosU1 * sinU2 - sinU1 * cosU2 * cosalpha) *
                    (cosU1 * sinU2 - sinU1 * cosU2 * cosalpha)
            sinsigma = sqrt(sinSqsigma)

            if (sinsigma == 0.0) return Vincenty(0.0)

            cossigma = sinU1 * sinU2 + cosU1 * cosU2 * cosalpha
            sigma = atan2(sinsigma, cossigma)

            val sinAlpha = cosU1 * cosU2 * sinalpha / sinsigma
            cosSqAlpha = 1 - sinAlpha * sinAlpha
            cos2sigmaM = cossigma - 2.0 * sinU1 * sinU2 / cosSqAlpha

            if (java.lang.Double.isNaN(cos2sigmaM)) cos2sigmaM = 0.0

            val C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha))
            alphaʹ = alpha
            alpha = L + (1 - C) * f * sinAlpha *
                    (sigma + C * sinsigma * (cos2sigmaM + C * cossigma * (-1 + 2.0 * cos2sigmaM * cos2sigmaM)))
        } while (abs(alpha - alphaʹ) > 1e-12 && --iterationLimit > 0)

        if (iterationLimit == 0.0) throw IllegalStateException("Formula failed to converge")

        val uSq = cosSqAlpha * (a * a - b * b) / (b * b)
        val A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)))
        val B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)))
        val deltasigma =
            (B * sinsigma * ((cos2sigmaM + (B / 4 * ((cossigma * (-1 + 2.0 * cos2sigmaM * cos2sigmaM) - B / 6 * cos2sigmaM *
                    (-3 + 4.0 * sinsigma * sinsigma) * (-3 + 4.0 * cos2sigmaM * cos2sigmaM)))))))
        val distance = b * A * (sigma - deltasigma)

        return Vincenty(distance)
    }

    fun vincentyDistance(standPoint: Point, forePoint: Point): Double {
        return vincenty(standPoint, forePoint).distance
    }

    private class Vincenty(distance: Double) {

        var distance: Double = 0.toDouble()

        init {
            this.distance = distance
        }
    }
}