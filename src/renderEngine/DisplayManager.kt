package renderEngine

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import java.net.Inet4Address

/**
 * Created by tauraaamui on 22/08/2016.
 */
class DisplayManager {

    //PUT THIS IN THE JVM ARGS TO GET LWJGL DEBUG STREAM: -Dorg.lwjgl.util.Debug=true

    companion object {

        private val WIDTH = 800
        private val HEIGHT = 600
        private val FPS_CAP = 120

        var rootWindowID: Long = -1

        fun createDisplay() {

            GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))

            if (!GLFW.glfwInit()) throw IllegalStateException("Unable to initialize GLFW")
            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3)
            if (Inet4Address.getLocalHost().hostName.toLowerCase() != "krweynb-alewis") {
                GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2)
                GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE)
                //for compatibility with Mac OS X
                GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE)
            } else {
                println("This is Adam's special (retarded) work laptop which has crappy Intel Graphics, turning off OpenGL flags")
            }
            rootWindowID = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Test", 0, 0)

            if (rootWindowID == 0L) throw RuntimeException("Failed to create window")

            centreDisplay()

            GLFW.glfwMakeContextCurrent(rootWindowID);
            GLFW.glfwSwapInterval(1)

            GL.createCapabilities()
            GLFW.glfwShowWindow(rootWindowID)
        }

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