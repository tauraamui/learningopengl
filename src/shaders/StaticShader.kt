package shaders

import co.uk.taurasystems.game.engine.utils.ShaderProgram
import java.io.File

/**
 * Created by tauraaamui on 22/08/2016.
 */
class StaticShader : ShaderProgram {

    private val VERTEX_FILE = File("src/shaders/vertexShader.txt")

    private val FRAGMENT_FILE = File("src/shaders/fragmentShader.txt")

    constructor() {
        createVertexShader(VERTEX_FILE)
        createFragmentShader(FRAGMENT_FILE)
        link()
    }

    override fun bindAttributes() {
        bindAttribute(0, "position")
    }
}
