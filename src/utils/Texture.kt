package utils

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL15
import org.lwjgl.stb.STBImage

import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import org.lwjgl.stb.STBImage.*;

/**
 * This class represents a texture.
 *
 * @author Heiko Brumme
 */
public class Texture {

    /**
     * Stores the handle of the texture.
     */
    private var id = 0

    /**
     * Width of the texture.
     */
    private var width = 0
    /**
     * Height of the texture.
     */
    private var height = 0

    /**
     * Creates a texture with specified width, height and data.
     *
     * @param width  Width of the texture
     * @param height Height of the texture
     * @param data   Picture Data in RGBA format
     */

    constructor(width: Int, height: Int, data: ByteBuffer) {
        id = GL11.glGenTextures()
        this.width = width
        this.height = height

        glBindTexture(GL_TEXTURE_2D, id)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data)
    }

    /**
     * Binds the texture.
     */

    fun bind() {GL11.glBindTexture(GL11.GL_TEXTURE_2D, id)}

    /**
     * Delete the texture.
     */
    fun delete() {GL11.glDeleteTextures(id)}

    /**
     * Gets the texture width.
     *
     * @return Texture width
     */
    fun getWidth(): Int {return width}

    /**
     * Gets the texture height.
     *
     * @return Texture height
     */
    fun getHeight(): Int {return height}

    fun getID(): Int {return id}

    /**
     * Load texture from file.
     *
     * @param path File path of the texture
     *
     * @return Texture from specified file
     */

    companion object {
        fun loadTexture(path: String): Texture {
            val w = BufferUtils.createIntBuffer(1)
            val h = BufferUtils.createIntBuffer(1)
            val comp = BufferUtils.createIntBuffer(1)

            stbi_set_flip_vertically_on_load(0)
            val image = stbi_load(path, w, h, comp, 4)

            if (image == null) throw RuntimeException("Failed to load texture from file: $path \n ${stbi_failure_reason()}")
            val width = w.get()
            val height = h.get()

            return Texture(width, height, image)
        }
    }
}