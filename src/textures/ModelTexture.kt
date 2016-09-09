package textures

/**
 * Created by tauraamui on 09/09/2016.
 */

class ModelTexture {

    private var textureID = -1

    constructor(textureID: Int) {
        this.textureID = textureID
    }

    fun getTextureID(): Int {return textureID}
}