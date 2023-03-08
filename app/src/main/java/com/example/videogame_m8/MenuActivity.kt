package com.example.videogame_m8

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    private var difficultyMultiplier = 1.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val easyButton = findViewById<RadioButton>(R.id.radioButtonEasy)
        val mediumButton = findViewById<RadioButton>(R.id.radioButtonMedium)
        val hardButton = findViewById<RadioButton>(R.id.radioButtonHard)
        val playButton = findViewById<Button>(R.id.play_button)
        val intent = Intent(this, MainActivity::class.java)

        easyButton.setOnClickListener { v: View? ->
            difficultyMultiplier = 0.5
            easyButton.isChecked = true
            mediumButton.isChecked = false
            hardButton.isChecked = false
            intent.putExtra("difficultyMultiplier", difficultyMultiplier)
        }
        mediumButton.setOnClickListener { v: View? ->
            difficultyMultiplier = 1.0
            easyButton.isChecked = false
            mediumButton.isChecked = true
            hardButton.isChecked = false
            intent.putExtra("difficultyMultiplier", difficultyMultiplier)
        }
        hardButton.setOnClickListener { v: View? ->
            difficultyMultiplier = 2.0
            easyButton.isChecked = false
            mediumButton.isChecked = false
            hardButton.isChecked = true
            intent.putExtra("difficultyMultiplier", difficultyMultiplier)
        }
        playButton.setOnClickListener { v: View? -> startActivity(intent) }
    }
}