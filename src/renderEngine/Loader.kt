package renderEngine

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import utils.Texture
import java.io.File
import java.nio.FloatBuffer
import java.nio.IntBuffer

/**
 * Created by tauraaamui on 22/08/2016.
 */

class Loader {

    private var vaos = IntegerArray()
    private var vbos = IntegerArray()
    private val textures = IntegerArray()

    fun loadToVAO(positions: FloatArray, indices: IntArray) : RawModel {
        val vaoID = createVAO()
        bindIndiciesBuffer(indices)
        storeDataInAttributeList(0, positions)
        unbindVAO()
        return RawModel(vaoID, indices.size)
    }

    fun loadTexture(fileName: String): Int {
        val texture = Texture.loadTexture(fileName)
        textures.add(texture.getID())
        return texture.getID()
    }

    fun cleanUp() {
        for (vao in vaos.toIntArray()) {
            GL30.glDeleteVertexArrays(vao)
        }
        for (vbo in vbos.toIntArray()) {
            GL15.glDeleteBuffers(vbo)
        }

        for (textureID in textures.toIntArray()) {
            GL11.glDeleteTextures(textureID)
        }
    }

    private fun createVAO(): Int {
        val vaoID = GL30.glGenVertexArrays()
        vaos.add(vaoID)
        GL30.glBindVertexArray(vaoID)
        return vaoID
    }

    private fun storeDataInAttributeList(attributeNumber: Int, data: FloatArray) {
        val vboID = GL15.glGenBuffers()
        vbos.add(vboID)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID)
        val floatBuffer = convertFloatArrayToBuffer(data)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    private fun unbindVAO() {
        GL30.glBindVertexArray(0)
    }

    private fun bindIndiciesBuffer(indices: IntArray) {
        val vboID = GL15.glGenBuffers()
        vbos.add(vboID)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID)
        val buffer = convertIntArrayToIntBuffer(indices)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
    }

    private fun convertIntArrayToIntBuffer(data: IntArray): IntBuffer {
        val buffer = BufferUtils.createIntBuffer(data.size)
        buffer.put(data)
        buffer.flip()
        return buffer
    }

    private fun convertFloatArrayToBuffer(data: FloatArray): FloatBuffer {
        val floatBuffer = BufferUtils.createFloatBuffer(data.size)
        floatBuffer.put(data)
        floatBuffer.flip()
        return floatBuffer
    }
}