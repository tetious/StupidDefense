package net.mazin.defense

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Json
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.viewport.FitViewport

class GameScreen : ScreenAdapter {
    private val stage : Stage
    private val camera = OrthographicCamera()
    private var paused = false;

    constructor() : super() {
        this.stage = Stage(FitViewport(1920f, 1080f, this.camera))
        this.stage.addActor(Background())
        val pathFile = Gdx.files.local("path.json")
        if(pathFile.exists()) {
            val pathPoints = Json().fromJson(arrayOf(Vector2()).javaClass, pathFile)
            pathPoints.forEach { p -> this.stage.addActor(PathPoint(p)) }
            Timer.schedule(object : Timer.Task() {
                override fun run() {
                    stage.addActor(Enemy(Vector2(1920f, 1080f)).follow(pathPoints.toList()))
                }
            }, 0.1f, 0.1f);
        }

        this.stage.addListener(object : InputListener() {
            override fun enter(event: InputEvent?, x: Float, y: Float, pointer: Int, fromActor: Actor?) {
                stage.keyboardFocus = event?.target
            }
            override fun exit(event: InputEvent?, x: Float, y: Float, pointer: Int, toActor: Actor?) {
                stage.keyboardFocus = toActor
            }
        })

        Gdx.input.inputProcessor = stage
    }

    override fun dispose() {
        Json().toJson(this.stage.actors.filter { a -> a is PathPoint }.map { a -> Vector2(a.x, a.y) },
                Gdx.files.local("path.json"))
        Gdx.app.log("GameScreen", "Disposed!")
        super.dispose()
    }

    override fun pause() {
        paused = true
        super.pause()
    }

    override fun resume() {
        paused = false
        super.resume()
    }

    override fun resize(width: Int, height: Int) {
        this.stage.viewport.update(width, height)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        if(!paused) stage.act(delta)
        stage.draw()
    }
}