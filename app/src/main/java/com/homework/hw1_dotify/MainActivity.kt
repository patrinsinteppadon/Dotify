package com.homework.hw1_dotify

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_song.*
import kotlin.random.Random

/**
 * WHAT'S NEW IN HW4:
 *  - Remember number of plays for each song using Application
 *  - Makes an HTTP request to an API using Volley
 *  - Converts results of JSON into Song objects using GSON
 *  - Bind images using Picasso.
 * */
class MainActivity : AppCompatActivity() {
    companion object {
        const val SONG_TITLE = "SongName"
        const val SONG_ARTIST = "SongArtist"
        const val SONG_IMG = "SongImage"
        const val SONG_PLAYS = "SongPlays"
    }
    private val textRecolor = R.color.colorPrimaryDark
    private var numPlays = Random.nextInt(1, 1001) // number of times the song has been played
    private var loggedIn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindSongInfo() // binds extras to page
        initPageElements()

    }

    private fun bindSongInfo() {
        val title = intent.getStringExtra(SONG_TITLE)
        val artist = intent.getStringExtra(SONG_ARTIST)
        val image = intent.getIntExtra(SONG_IMG, -1)
        val plays  = intent.getIntExtra(SONG_PLAYS, -1)

        if (title != null && artist != null && image != -1 &&  plays != -1) {
            tvSongTitle.text = title.toString()
            tvSongArtist.text = artist.toString()
//            imgSongImg.setImageResource(image) // replace with picasso
            Picasso.get().load(image).into(imgSongImg);
            numPlays = plays
        } else {
            Toast.makeText(this, "Whoops--something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initPageElements() {
        // init "change user" button
        btnChangeUser.setOnClickListener {changeUser()}

        // init play/next/prev buttons
        initControlPanel()

        // init text color change button
        imgSongImg.setOnLongClickListener {
            val textViews: List<TextView> = listOf(tvUsername, tvSongTitle, tvSongArtist, tvSongPlays)
            for (text in textViews) {
                recolorText(text)
            }
            true // lol setOnLongClickListener requires a boolean return. Could you explain why?
        }

        // init screen
        updatePlays()
    }

    private fun changeUser() {
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

    /**
     * helper function to onCreate.
     * initializes click listeners for the "next", "previous", and "play" buttons
     */
    private fun initControlPanel() {
        // init "previous song" button
        btnPrev.setOnClickListener {
            displayToast("Skipping to previous track")
        }

        // init "next song" button
        btnNext.setOnClickListener {
            displayToast("Skipping to next track")
        }

        // init "play" button
        btnPlay.setOnClickListener {
            numPlays++
            updatePlays()
        }
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

    /**
     * extra credit function.
     * Long pressing on the cover image changes the color of all the text views to a different color.
     */
    private fun recolorText(view: TextView) {
        view.setTextColor(ContextCompat.getColor(this, textRecolor))
    }
}
