package models

import textures.ModelTexture

/**
 * Created by tauraamui on 09/09/2016.
 */
class TexturedModel {

    private var rawModel: RawModel = RawModel(-1, -1)
    private var modelTexture: ModelTexture? = null

    constructor(rawModel: RawModel, modelTexture: ModelTexture) {this.rawModel = rawModel; this.modelTexture = modelTexture}

    fun getRawModel(): RawModel {return rawModel}
    fun getModelTexture(): ModelTexture {return modelTexture!!}
}