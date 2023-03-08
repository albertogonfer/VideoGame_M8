package com.example.videogame_m8

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    var juego: Juego? = null
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        juego = findViewById<View>(R.id.Pantalla) as Juego
        val obs: ViewTreeObserver = juego!!.getViewTreeObserver()
        obs.addOnGlobalLayoutListener { // Sólo se puede averiguar el ancho y alto una vez ya se ha pintado el layout. Por eso se calcula en este listener
            juego!!.ancho = juego!!.getWidth()
            juego!!.alto = juego!!.getHeight()
            juego!!.posX = juego!!.ancho / 2
            juego!!.posY = juego!!.alto - 50
            juego!!.radio = 50
            juego!!.posMonedaY = 50
        }

        //Ejecutamos cada 20 milisegundos
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post { //Cada x segundos movemos la moneda 10dp
                    juego!!.posMonedaY += 10
                    //refreca la pantalla y llama al draw
                    juego!!.invalidate()
                }
            }
        }, 0, 20)
    }
}