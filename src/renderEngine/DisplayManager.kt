package renderEngine

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.opengl.GL

/**
 * Created by tauraaamui on 22/08/2016.
 */
class DisplayManager {

    companion object {

        private val WIDTH = 800
        private val HEIGHT = 600
        private val FPS_CAP = 120

        var rootWindowID: Long = -1

        fun LWJGL3_createDisplay() {

            GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))

            if (!GLFW.glfwInit()) throw IllegalStateException("Unable to initialize GLFW")

            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3)
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3)
            GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE)
            rootWindowID = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Test", 0, 0)

            if (rootWindowID == 0L) throw RuntimeException("Failed to create window")

            centreDisplay()

            GLFW.glfwMakeContextCurrent(rootWindowID);
            GLFW.glfwSwapInterval(1)

            GL.createCapabilities()
            GLFW.glfwShowWindow(rootWindowID)
        }

        /*
        fun createDisplay() {

            val contextAttribs = ContextAttribs(3,2)
            contextAttribs.withForwardCompatible(true)
            contextAttribs.withProfileCore(true)

            try {
                Display.setDisplayMode(DisplayMode(WIDTH, HEIGHT))
                Display.setTitle("Test")
                Display.create(PixelFormat(), contextAttribs)
            } catch (e: LWJGLException) {
                e.printStackTrace()
            }

            GL11.glViewport(0, 0, WIDTH, HEIGHT)
        }
        */

        fun centreDisplay() {
            GLFW.glfwMakeContextCurrent(rootWindowID)
            val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
            GLFW.glfwSetWindowPos(rootWindowID, (vidmode.width() - WIDTH) / 2, (vidmode.height() - HEIGHT) /2)
        }

        fun updateDisplay() {
            GLFW.glfwPollEvents()
            GLFW.glfwSwapBuffers(rootWindowID)
        }

        fun closeDisplay() {
            GLFW.glfwDestroyWindow(rootWindowID)
        }
    }
}