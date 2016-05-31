package net.mazin.defense

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo
import com.badlogic.gdx.scenes.scene2d.utils.DragListener
import com.badlogic.gdx.utils.Align

class PathPoint(x: Float, y: Float) : Actor() {

    constructor(vector2: Vector2) : this(vector2.x, vector2.y)

    init {
        this.setPosition(x, y)
        this.setSize(20f, 20f)
        this.setOrigin(Align.center)
        this.addListener(object : DragListener() {
            override fun drag(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                Gdx.app.log("PathPoint", "drag")
                if(event == null) { return }
                addAction(moveTo(event.stageX - originX, event.stageY - originX))
            }

            override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
                Gdx.app.log("PathPoint", "keyDown")

                if(keycode == Input.Keys.DEL || keycode == Input.Keys.FORWARD_DEL) {
                    remove()
                    return true
                }

                return false
            }
        })
    }
}