package renderEngine

import entities.Entity
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import shaders.StaticShader
import utils.maths.models.Maths

/**
 * Created by tauraaamui on 22/08/2016.
 */
class Renderer {

    fun prepare() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
        GL11.glClearColor(1f, 0f, 0f, 1f)
    }

    fun render(entity: Entity, shader: StaticShader) {
        val rawModel = entity.texturedModel.rawModel
        GL30.glBindVertexArray(rawModel.vaoID)
        GL20.glEnableVertexAttribArray(0)
        GL20.glEnableVertexAttribArray(1)
        val transformationMatrix = Maths.createTransformationMatrix(entity.position, entity.rotation, entity.scale)
        shader.loadTransformationMatrix(transformationMatrix)
        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.texturedModel.modelTexture?.getTextureID()!!)
        GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.vertextCount, GL11.GL_UNSIGNED_INT, 0)
        GL20.glDisableVertexAttribArray(0)
        GL20.glDisableVertexAttribArray(1)
        GL30.glBindVertexArray(0)
    }
}