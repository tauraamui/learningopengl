package utils

class Vector3f {
    var x: Float = 0.toFloat()
    var y: Float = 0.toFloat()
    var z: Float = 0.toFloat()

    constructor(xpos: Float, ypos: Float, zpos: Float) {
        x = xpos
        y = ypos
        z = zpos
    }

    constructor(xpos: Double, ypos: Double, zpos: Double) {
        x = xpos.toFloat()
        y = ypos.toFloat()
        z = zpos.toFloat()
    }

    val magnitude: Double
        get() = Math.sqrt(x * x + y * y + (z * z).toDouble())

    fun normalize(): Vector3f {
        val length = magnitude
        return Vector3f.divide(this, length.toFloat())
    }

    fun lookAt(p: Vector3f): Vector3f {
        return Vector3f.subtract(this, p).divide(p.magnitude.toFloat())
    }


    fun crossProduct(other: Vector3f): Vector3f {
        return Vector3f(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x)
    }


    fun scale(scale: Float): Vector3f {
        x *= scale
        y *= scale
        z *= scale
        return this
    }

    fun multiply(vec: Vector3f): Vector3f {
        x *= vec.x
        y *= vec.y
        z *= vec.z
        return this
    }

    fun divide(div: Float): Vector3f {
        x /= div
        y /= div
        z /= div
        return this
    }

    fun divide(vec: Vector3f): Vector3f {
        x /= vec.x
        y /= vec.y
        z /= vec.z
        return this
    }

    fun add(vec: Vector3f): Vector3f {
        x += vec.x
        y += vec.y
        z += vec.z
        return this
    }

    fun subtract(vec: Vector3f): Vector3f {
        x -= vec.x
        y -= vec.y
        z -= vec.z
        return this
    }

    fun negate(): Vector3f {
        x = -x
        y = -y
        z = -z
        return this
    }

    fun Clone(): Vector3f {
        return Vector3f(x, y, z)
    }

    override fun toString(): String {
        return "X: $x\tY: $y\tZ: $z"
    }

    fun toGLSLConstructor(): String {
        return "Vec3($x,$y,$z)"
    }

    companion object {
        fun multiply(vec1: Vector3f, vec2: Vector3f): Vector3f {
            return Vector3f(vec1.x * vec2.x, vec1.y * vec2.y, vec1.z * vec2.z)
        }

        fun multiply(vec1: Vector3f, scaler: Float): Vector3f {
            return Vector3f(vec1.x * scaler, vec1.y * scaler, vec1.z * scaler)
        }

        fun divide(vec1: Vector3f, div: Float): Vector3f {
            return Vector3f(vec1.x / div, vec1.y / div, vec1.z / div)
        }

        fun divide(vec1: Vector3f, vec2: Vector3f): Vector3f {
            return Vector3f(vec1.x / vec2.x, vec1.y / vec2.y, vec1.z / vec2.z)
        }

        fun add(vec1: Vector3f, vec2: Vector3f): Vector3f {
            return Vector3f(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z)
        }

        fun subtract(vec1: Vector3f, vec2: Vector3f): Vector3f {
            return Vector3f(vec1.x - vec2.x, vec1.y - vec2.y, vec1.z - vec2.z)
        }
    }
}
