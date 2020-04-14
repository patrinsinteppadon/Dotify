package com.homework.hw1_dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randomizeSongPlays()
    }

    /**
     * set text for tvSongPlays to a random number between 1 and 1000
     * runs on startup
     */
    private fun randomizeSongPlays() {
        var tvSongPlays = findViewById<TextView>(R.id.tvSongPlays)
        tvSongPlays.text = Random.nextInt(1, 1001).toString() + " plays"
    }
}
