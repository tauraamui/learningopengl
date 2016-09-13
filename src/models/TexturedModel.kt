package models

import textures.ModelTexture

/**
 * Created by tauraamui on 09/09/2016.
 */
class TexturedModel {

    var rawModel: RawModel = RawModel(-1, -1)
    var modelTexture: ModelTexture? = null

    constructor(rawModel: RawModel, modelTexture: ModelTexture) {this.rawModel = rawModel; this.modelTexture = modelTexture}
}