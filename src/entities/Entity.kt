package entities

import models.RawModel
import models.TexturedModel
import textures.ModelTexture
import utils.maths.JOML.Vector3f

/**
 * Created by tauraaamui on 11/09/2016.
 */

class Entity {

    var texturedModel: TexturedModel = TexturedModel(RawModel(-1, -1), ModelTexture(-1))
    var position = Vector3f(0f,0f,0f)
    var rotation = Vector3f(0f,0f,0f)
    var scale = 0f

    constructor(texturedModel: TexturedModel, position: Vector3f, rotation: Vector3f, scale: Float) {
        this.texturedModel = texturedModel
        this.position = position
        this.rotation = rotation
        this.scale = scale
    }

    fun increasePosition(position: Vector3f) {this.position.x += position.x; this.position.y += position.y; this.position.z += position.z}
    fun increaseRotation(rotation: Vector3f) {this.rotation.x += rotation.x; this.rotation.y += rotation.y; this.rotation.z += rotation.z}
}