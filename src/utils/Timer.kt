package co.uk.taurasystems.game.engine.utils

/**
 * Created by tauraaamui on 17/08/2016.
 */
class Timer {

    private var lastLoopTime: Double = (0).toDouble()

    fun init() {
        lastLoopTime = getTime()
    }

    fun getTime(): Double {
        return System.nanoTime() / (1000000000.0).toDouble()
    }

    fun getElapsedTime(): Float {
        val time = getTime()
        val elapsedTime: Float = (time - lastLoopTime).toFloat()
        lastLoopTime = time
        return elapsedTime
    }

    fun getLastLoopTime(): Double {return lastLoopTime}
}