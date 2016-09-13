package engineTester

import entities.Entity
import models.TexturedModel
import org.lwjgl.glfw.GLFW
import renderEngine.DisplayManager
import renderEngine.Loader
import renderEngine.Renderer
import shaders.StaticShader
import textures.ModelTexture
import utils.maths.JOML.Vector3f

/**
 * Created by tauraaamui on 22/08/2016.
 */



fun main(args: Array<String>) {

    DisplayManager.createDisplay()

    val staticShader = StaticShader()

    val loader = Loader()
    val renderer = Renderer()
    listOf(Float)

    val verticies = floatArrayOf(-0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f)
    val indices = intArrayOf(0, 1, 3, 3, 1, 2)
    val textureCoords = floatArrayOf(0f,0f, 0f,1f, 1f,1f, 1f,0f)

    val model = loader.loadToVAO(verticies, textureCoords, indices)
    val modelTexture = ModelTexture(loader.loadTexture("image"))
    val staticModel = TexturedModel(model, modelTexture)

    val entity = Entity(staticModel, position = Vector3f(-1f,0f,0f), rotation = Vector3f(0f,0f,0f), scale = 1f)

    while (!GLFW.glfwWindowShouldClose(DisplayManager.rootWindowID)) {
        renderer.prepare()
        staticShader.bind()
        renderer.render(entity, staticShader)
        staticShader.unbind()
        DisplayManager.updateDisplay()
    }

    staticShader.cleanup()
    loader.cleanUp()
    DisplayManager.closeDisplay()
}