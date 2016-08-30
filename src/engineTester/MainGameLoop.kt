package engineTester

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import renderEngine.DisplayManager
import renderEngine.Loader
import renderEngine.Renderer
import shaders.StaticShader

/**
 * Created by tauraaamui on 22/08/2016.
 */



fun main(args: Array<String>) {

    DisplayManager.LWJGL3_createDisplay()

    val staticShader = StaticShader()

    val loader = Loader()
    val renderer = Renderer()
    listOf(Float)

    val verticies: FloatArray = floatArrayOf(-0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f)
    val indices: IntArray = intArrayOf(0, 1, 3, 3, 1, 2)

    val model = loader.loadToVAO(verticies, indices)

    while (!GLFW.glfwWindowShouldClose(DisplayManager.rootWindowID)) {
        renderer.prepare()
        staticShader.bind()
        renderer.render(model)
        staticShader.unbind()
        DisplayManager.updateDisplay()
    }

    staticShader.cleanup()
    loader.cleanUp()
    DisplayManager.closeDisplay()
}