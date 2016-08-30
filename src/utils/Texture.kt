package utils

import org.lwjgl.BufferUtils
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import org.lwjgl.stb.STBImage
import sun.nio.ch.IOUtil
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.channels.SeekableByteChannel
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by tauraaamui on 22/08/2016.
 */
class Texture {

    var id = -1
    var width = 0
    var height = 0

    constructor(file: File) {

        val imageBuffer = ioResourceToByteBuffer(file.absolutePath, 8 * 1024)
        

        var bufferedImage: BufferedImage? = null
        try {
            bufferedImage = ImageIO.read(file)
            width = bufferedImage.width
            height = bufferedImage.height

            val pixelsRaw = bufferedImage.getRGB(0, 0, width, height, null, 0, width)
            val pixels = BufferUtils.createByteBuffer(width * height * 4)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun resizeBuffer(buffer: ByteBuffer, newCapacity: Int): ByteBuffer {
        val newBuffer = BufferUtils.createByteBuffer(newCapacity)
        buffer.flip()
        newBuffer.put(buffer)
        return newBuffer
    }

    @Throws(IOException::class)
    fun ioResourceToByteBuffer(resource: String, bufferSize: Int): ByteBuffer {
        var buffer: ByteBuffer? = null

        val path = Paths.get(resource)
        if (Files.isReadable(path)) {
            val fc = Files.newByteChannel(path)
            buffer = BufferUtils.createByteBuffer(fc.size().toInt() + 1)
            while (fc.read(buffer) != -1) {
                println(fc.position())
            }
        } else {
            try {
                val source = Texture::class.java.getResourceAsStream(resource)
                val rbc = Channels.newChannel(source)
                buffer = BufferUtils.createByteBuffer(bufferSize)

                while (true) {
                    val bytes = rbc.read(buffer)
                    if (bytes == -1) break
                    if (buffer?.remaining() == 0)
                        buffer = resizeBuffer(buffer!!, buffer.capacity() * 2)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        buffer?.flip()
        return buffer!!
    }
}