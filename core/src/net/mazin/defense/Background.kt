package net.mazin.defense

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener

class Background : Actor() {
    private val background = Texture("bg.png")

    init {
        //this.setSize(this.stage.width, this.stage.height)
        this.setSize(1920f, 1080f)
        this.addListener(object: InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                Gdx.app.log("Background", "Hi from touchDown")
                stage.addActor(PathPoint(x, y))
                return false
            }
        })
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(background, 0f, 0f)
    }

}