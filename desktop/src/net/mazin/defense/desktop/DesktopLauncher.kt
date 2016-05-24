package net.mazin.defense.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import net.mazin.defense.DefenseGame

fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration()
    config.width = 1920
    config.height = 1080
    LwjglApplication(DefenseGame(), config)
}
