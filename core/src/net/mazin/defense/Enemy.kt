package net.mazin.defense

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.utils.Align

class Enemy(position: Vector2) : Actor() {
    private val ship: TextureRegion

    init {
        this.setPosition(position.x, position.y)
        val tex = Texture("sprites.0.png")
        this.ship = TextureRegion(tex, 128, 128)
        this.width = 64f
        this.height = 64f
        this.setOrigin(Align.center)
        this.addAction(forever(rotateBy(10f, 0.1f)))
    }

    override fun act(delta: Float) {

        //this.rotateBy(10f)

        super.act(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(this.ship, this.x, this.y, this.width / 2, this.height / 2, this.width, this.height, this.scaleX,
                this.scaleY, this.rotation)
    }

    fun goBoom() {
        // TODO: scoring, explosion effect, etc
        remove()
    }

    fun follow(pathPoints: List<Vector2>): Actor? {
        // todo: follow the path at a fixed rate
        // probably by manually moving instead of using actions

        val me = Vector2(this.x, this.y)
        var points = pathPoints.toMutableList()
        var first = pathPoints.sortedBy { p -> me.dst(p) }.first()
        points.remove(first)
        var sorted = mutableListOf(first)
        while(points.any()) {
            val last = sorted.last()
            val nextNearest = points.sortedBy { p -> last.dst(p) }.first()
            points.remove(nextNearest)
            sorted.add(nextNearest)
        }

        this.addAction(sequence( *sorted.map { p -> moveTo(p.x - this.originX, p.y - this.originY, 1f) }.toTypedArray(),
                run(Runnable { goBoom() })))

        return this;
    }
}