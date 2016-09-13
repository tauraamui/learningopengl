package shaders

import co.uk.taurasystems.game.engine.utils.ShaderProgram
import utils.maths.JOML.Matrix4f
import java.io.File

/**
 * Created by tauraaamui on 22/08/2016.
 */
class StaticShader : ShaderProgram {

    private val VERTEX_FILE = File("src/shaders/vertexShader.txt")

    private val FRAGMENT_FILE = File("src/shaders/fragmentShader.txt")

    private var location_transformationMatrix = -1

    constructor() {
        createVertexShader(VERTEX_FILE)
        createFragmentShader(FRAGMENT_FILE)
        link()
        getAllUniformLocations()
    }

    override fun bindAttributes() {
        bindAttribute(0, "position")
        bindAttribute(1, "textureCoordinates")
    }

    override fun getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix")
    }

    fun loadTransformationMatrix(matrix: Matrix4f) {
        super.loadMatrix(location_transformationMatrix, matrix)
    }
}
