package co.uk.taurasystems.game.engine.utils

/**
 * Created by tauraaamui on 18/08/2016.
 */

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL20.*
import utils.maths.JOML.Matrix4f
import utils.maths.JOML.Vector3f
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.nio.ByteBuffer

abstract class ShaderProgram {

    private var programID = 0
    private  var vertexShaderID = 0
    private var fragmentShaderID = 0

    private val matrixBuffer = BufferUtils.createFloatBuffer(16)

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
            var shaderCode = ""
            val bufferedReader = BufferedReader(FileReader(file))
            val charArray = CharArray(8192)
            bufferedReader.read(charArray)
            bufferedReader.close()
            shaderCode = String(charArray)
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
        //if (glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) throw Exception("Warning validating Shader code: " + glGetShaderInfoLog(programID, 1024))
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

    fun loadFloat(location: Int, value: Float) {
        GL20.glUniform1f(location, value)
    }

    fun loadVector(location: Int, vector: Vector3f) {
        GL20.glUniform3f(location, vector.x, vector.y, vector.z)
    }

    fun loadBoolean(location: Int, value: Float) {
        var toLoad = 0f
        if (value == 1.0f) toLoad = 1f
        GL20.glUniform1f(location, toLoad)
    }

    fun loadMatrix(location: Int, matrix: Matrix4f) {
        GL20.glUniformMatrix4fv(location, false, matrix.buffer)
    }

    abstract fun getAllUniformLocations()

    fun getUniformLocation(uniformName: String): Int {
        val location = GL20.glGetUniformLocation(programID, uniformName)
        return location
    }
}