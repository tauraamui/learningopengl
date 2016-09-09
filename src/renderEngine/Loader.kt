package renderEngine

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray
import models.RawModel
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import utils.Texture
import java.nio.FloatBuffer
import java.nio.IntBuffer

/**
 * Created by tauraaamui on 22/08/2016.
 */

class Loader {

    private var vaos = IntegerArray()
    private var vbos = IntegerArray()
    private val textures = IntegerArray()

    fun loadToVAO(positions: FloatArray, textureCoords: FloatArray, indices: IntArray) : RawModel {
        val vaoID = createVAO()
        bindIndiciesBuffer(indices)
        storeDataInAttributeList(0, 3, positions)
        storeDataInAttributeList(1, 2, textureCoords)
        unbindVAO()
        return RawModel(vaoID, indices.size)
    }

    fun loadTexture(fileName: String): Int {
        val custom_texture = Texture.loadTexture("res/image.png")
        textures.add(custom_texture.getID())
        return custom_texture.getID()
    }

    fun cleanUp() {
        vaos.toIntArray().forEach { GL30.glDeleteVertexArrays(it) }
        vbos.toIntArray().forEach { GL15.glDeleteBuffers(it) }
        textures.toIntArray().forEach { GL11.glDeleteTextures(it) }
    }

    private fun createVAO(): Int {
        val vaoID = GL30.glGenVertexArrays()
        vaos.add(vaoID)
        GL30.glBindVertexArray(vaoID)
        return vaoID
    }

    private fun storeDataInAttributeList(attributeNumber: Int, coordinateSize: Int, data: FloatArray) {
        val vboID = GL15.glGenBuffers()
        vbos.add(vboID)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID)
        val floatBuffer = convertFloatArrayToBuffer(data)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0)
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