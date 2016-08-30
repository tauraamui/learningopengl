/*
DON'T BE A DICK PUBLIC LICENSE TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

Do whatever you like with the original work, just don't be a dick.

Being a dick includes - but is not limited to - the following instances:

1a. Outright copyright infringement - Don't just copy this and change the name.
1b. Selling the unmodified original with no work done what-so-ever, that's REALLY being a dick.
1c. Modifying the original work to contain hidden harmful content. That would make you a PROPER dick.

If you become rich through modifications, related works/services, or supporting the original work, share the love. Only a dick would make loads off this work and not buy the original work's creator(s) a pint.

Code is provided with no warranty. Using somebody else's code and bitching when it goes wrong makes you a DONKEY dick. Fix the problem yourself. A non-dick would submit the fix back.
*/

package co.uk.taurasystems.game.engine.utils


class Vector2f {

    private var X: Float = 0f
    private var Y: Float = 0f

    constructor(X: Float, Y: Float) {
        this.X = X
        this.Y = Y
    }

    fun X(): Float {
        return X
    }

    fun setX(f: Float) {
        X = f
    }

    fun Y(): Float {
        return Y
    }

    fun setY(y: Float) {
        Y = y
    }

    fun scale(scale: Float) {
        X *= scale
        Y *= scale
    }

    fun add(vector: Vector2f) {
        X += vector.X
        Y += vector.Y
    }

    val magnitude: Float
        get() = Math.sqrt((X * X + Y * Y).toDouble()).toFloat()

    val magnitudeSquared: Float
        get() = (X * X + Y * Y).toFloat()

    fun normalize() {
        val length = magnitude
        X = X / length
        Y = Y / length
    }
}
