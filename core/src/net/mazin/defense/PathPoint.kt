package net.mazin.defense

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
        this.debug = false;
        this.addListener(object : DragListener() {
            override fun drag(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                if(event == null) { return }
                addAction(moveTo(event.stageX - originX, event.stageY - originX))
            }
            override fun enter(event: InputEvent?, x: Float, y: Float, pointer: Int, fromActor: Actor?) {
                stage?.keyboardFocus = this@PathPoint
            }
            override fun exit(event: InputEvent?, x: Float, y: Float, pointer: Int, toActor: Actor?) {
                stage?.keyboardFocus = null
            }
            override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
                if(keycode == Input.Keys.DEL || keycode == Input.Keys.FORWARD_DEL) {
                    remove()
                    return true
                }

                return false
            }
        })
    }

    override fun drawDebug(shapes: ShapeRenderer?) {
        shapes?.circle(this.originX + this.x, this.originY + this.y, this.width * .70f)
    }
}