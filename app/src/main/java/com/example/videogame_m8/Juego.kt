package com.example.videogame_m8

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.util.*

// Extensión de una View. Totalmente necesario para dibujar
class Juego : View {
    var ancho = 0
    var alto = 0
    var escala = 0f
    var posX = 0
    var posY = 0
    var radio = 0
    var posMonedaX = 0
    var posMonedaY = 0
    var vidas = 3
    var puntos = 0
    private val gestos: GestureDetector? = null
    private var rectCesta: RectF? = null
    private var rectMoneda: RectF? = null
    private val random = Random()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    //Sección que capta los eventos del usuario
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // you may need the x/y location
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_UP -> {}
            MotionEvent.ACTION_MOVE -> {
                posX = event.x.toInt()
                radio = 50
                this.invalidate()
            }
        }
        return true
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //Definimos los objetos a pintar
        val fondo = Paint()
        val cesta = Paint()
        val moneda = Paint()
        val puntosDraw = Paint()
        val vidasDraw = Paint()
        //Definimos los colores de los objetos a pintar
        fondo.color = Color.BLACK
        fondo.style = Paint.Style.FILL_AND_STROKE
        cesta.color = Color.YELLOW
        cesta.style = Paint.Style.FILL_AND_STROKE
        moneda.color = Color.RED
        moneda.style = Paint.Style.FILL_AND_STROKE
        puntosDraw.textAlign = Paint.Align.RIGHT
        puntosDraw.textSize = 50f
        puntosDraw.color = Color.WHITE
        vidasDraw.textAlign = Paint.Align.LEFT
        vidasDraw.textSize = 50f
        vidasDraw.color = Color.WHITE
        //Pinto rectángulo con un ancho y alto de 1000 o de menos si la pantalla es menor.
        canvas.drawRect(Rect(0, 0, ancho, alto), fondo)
        // Pinto la pelota. La Y la implementa el timer y la X la pongo aleatoreamente en cuanto llega al final
        rectCesta = RectF(
            (posX - radio).toFloat(),
            (posY - radio).toFloat(),
            (posX + radio).toFloat(),
            (posY + radio).toFloat()
        )
        canvas.drawOval(rectCesta!!, cesta)
        //Pintamos moneda
        if (posMonedaY > alto) {
            posMonedaY = 50
            posMonedaX = random.nextInt(ancho)
        }
        rectMoneda = RectF(
            (posMonedaX - radio).toFloat(),
            (posMonedaY - radio).toFloat(),
            (posMonedaX + radio).toFloat(),
            (posMonedaY + radio).toFloat()
        )
        canvas.drawOval(rectMoneda!!, moneda)
        // calculo de interseccion
        detectarColisionMonedaCesta()
        //Pintamos puntos
        canvas.drawText("Puntos: $puntos", (ancho - 150).toFloat(), 150f, puntosDraw)
        //Pintamos vidas
        canvas.drawText("Vidas: $vidas", 150f, 150f, vidasDraw)
    }

    private fun resetMoneda() {
        posMonedaY = alto - 50
        posMonedaX = random.nextInt(ancho)
    }
    private fun detectarColisionMonedaCesta() {
        if (RectF.intersects(rectCesta!!, rectMoneda!!)) {
            resetMoneda()
            puntos++
        }else if (posMonedaY < 0 && vidas > 1) {
            resetMoneda()
            vidas--
        }
    }

}