package utils.maths.models

import utils.maths.JOML.Matrix4f
import utils.maths.JOML.Vector3f
import utils.maths.JOML.Vector4f

/**
 * Created by tauraaamui on 10/09/2016.
 */

class Maths {

    companion object {
        fun createTransformationMatrix(translation: Vector3f, rotation: Vector3f, scale: Float): Matrix4f {
            val matrix = Matrix4f()
            matrix.translation(translation)
            //matrix.translate(translation).rotate(0f, Vector3f(1f,0f,0f)).rotate(0f, Vector3f(0f,1f,0f)).rotate(0f, Vector3f(0f,0f,1f)).scale(1f)
            /*
            println("${matrix.m00()}, ${matrix.m01()}, ${matrix.m02()}, ${matrix.m03()}")
            println("${matrix.m10()}, ${matrix.m11()}, ${matrix.m12()}, ${matrix.m13()}")
            println("${matrix.m20()}, ${matrix.m21()}, ${matrix.m22()}, ${matrix.m23()}")
            println("${matrix.m30()}, ${matrix.m31()}, ${matrix.m32()}, ${matrix.m33()}")
            */
            println(matrix)
            return matrix
        }
    }

}
