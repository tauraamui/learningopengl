package utils

import java.io.File
import java.io.FileInputStream

/**
 * Created by tauraaamui on 30/08/2016.
 */
class TextureLoader {

    companion object {

        var atomicInteger = 0

        fun getTexture(file: File): Texture {
            val texture = Texture(file)
            texture.id = atomicInteger
            atomicInteger++
            return texture
        }
    }
}