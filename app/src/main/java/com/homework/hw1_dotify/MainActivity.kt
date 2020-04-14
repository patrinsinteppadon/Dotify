package com.homework.hw1_dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var numPlays = Random.nextInt(1, 1001) // number of times the song has been played
    private var loggedIn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init number of plays
        btnPlay.setOnClickListener {
            numPlays++
            updatePlays()
        }

        // init "change user" button
        btnChangeUser.setOnClickListener {
            if (loggedIn) {
                etUsername.text.clear()
                etUsername.visibility = View.VISIBLE
                tvUsername.visibility = View.INVISIBLE
                btnChangeUser.text = getString(R.string.apply) // displays as "Apply"
                loggedIn = false
            } else if (!etUsername.text.isBlank()) {
                tvUsername.text = etUsername.text
                tvUsername.visibility = View.VISIBLE
                etUsername.visibility = View.INVISIBLE
                btnChangeUser.text = getString(R.string.change_user) // displays as "Change User"
                loggedIn = true
            } else {
                displayToast("Please enter a username")
            }
        }

        // init "previous song" button
        btnPrev.setOnClickListener {
            displayToast("Skipping to previous track")
        }

        // init "next song" button
        btnNext.setOnClickListener {
            displayToast("Skipping to next track")
        }

        // init text color change button
        imgSongImg.setOnLongClickListener() {
            tvUsername
        }

        // init screen
        updatePlays()
    }

    /**
     * binds current num of song plays to the screen
     */
    private fun updatePlays() {
        tvSongPlays.text = "$numPlays ${getString(R.string.plays)}" // displays as: "x plays"
    }

    /**
     * prototype function.
     * displays message explaining intended behavior of final product.
     */
    private fun displayToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
