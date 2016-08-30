package co.uk.taurasystems.game.engine.utils

/**
 * Created by tauraaamui on 18/08/2016.
 */

import com.sun.prism.GraphicsPipeline
import org.lwjgl.opengl.GL20.*
import java.awt.font.TextAttribute
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

abstract class ShaderProgram {

    private var programID = 0
    private  var vertexShaderID = 0
    private var fragmentShaderID = 0

    @Throws(Exception::class)
    constructor() {
        programID = glCreateProgram()
        if (programID == 0) throw Exception("Could not create shader program...")
    }

    fun createVertexShader(shaderFile: File) {
        vertexShaderID = createShader(getShaderSource(shaderFile), GL_VERTEX_SHADER)
    }

    @Throws(Exception::class)
    fun createFragmentShader(shaderFile: File) {
        fragmentShaderID = createShader(getShaderSource(shaderFile), GL_FRAGMENT_SHADER)
    }

    private fun getShaderSource(file: File): String {
        try {
            val shaderCode = StringBuilder()
            val bufferedReader = BufferedReader(FileReader(file))

            bufferedReader.forEachLine {
                shaderCode.append(it).append("\n")
            }
            bufferedReader.close()
            return shaderCode.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
    }

    private fun createShader(shaderCode: String, shaderType: Int): Int {
        val shaderId = glCreateShader(shaderType)
        if (shaderId == 0) throw  Exception("Error creating shader. Code: " + shaderId)

        glShaderSource(shaderId, shaderCode)
        glCompileShader(shaderId)

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) throw Exception("Error compiling shader code: " + glGetShaderInfoLog(shaderId, 1024))

        glAttachShader(programID, shaderId)

        return shaderId
    }

    @Throws(Exception::class)
    fun link() {
        glLinkProgram(programID)
        if (glGetProgrami(programID, GL_LINK_STATUS) == 0) throw Exception("Error linking shader code: " + glGetShaderInfoLog(programID, 1024))

        glValidateProgram(programID)
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) throw Exception("Warning validating Shader code: " + glGetShaderInfoLog(programID, 1024))
    }

    fun bind() {
        glUseProgram(programID)
    }

    fun unbind() {
        glUseProgram(0)
    }

    protected abstract fun bindAttributes()

    protected fun bindAttribute(attribute: Int, variableName: String) {
        glBindAttribLocation(programID, attribute, variableName)
    }

    fun cleanup() {
        unbind()
        if (programID != 0) {
            if (vertexShaderID != 0) {
                glDetachShader(programID, vertexShaderID)
            }
            if (fragmentShaderID != 0) {
                glDetachShader(programID, fragmentShaderID)
            }
            glDeleteProgram(programID)
        }
    }
}