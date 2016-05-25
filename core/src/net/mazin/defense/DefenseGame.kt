package net.mazin.defense

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx

class DefenseGame : Game() {
    override fun create() {
        this.setScreen(GameScreen())
    }

    override fun dispose() {
        this.screen.dispose()
        super.dispose()
    }

    fun Application.log(msg: String) {
        Gdx.app.log("Main", msg)
    }
}



