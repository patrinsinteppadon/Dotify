package com.homework.hw1_dotify

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.homework.hw1_dotify.MainActivity.Companion.SONG_ARTIST
import com.homework.hw1_dotify.MainActivity.Companion.SONG_IMG
import com.homework.hw1_dotify.MainActivity.Companion.SONG_TITLE

class SongListActivity : AppCompatActivity() {
    private var currentSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        initRecyclerView()
        initMiniPlayer()
    }

    private fun initRecyclerView() {
        val listOfSongs = SongDataProvider.getAllSongs()
        val songListAdapter = SongListAdapter(listOfSongs)
        songListAdapter.onSongClickListener = {
            currentSong = it
            findViewById<TextView>(R.id.tvCurrentSong).text = it.title + " - " + it.artist
        }

        findViewById<RecyclerView>(R.id.rvSongList).adapter = songListAdapter
    }

    private fun openBigPlayer(song: Song) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra(SONG_TITLE, song.title)
        intent.putExtra(SONG_ARTIST, song.artist)
        intent.putExtra(SONG_IMG, song.largeImageID)
        startActivity(intent)
    }

    /** part 3 of the hw */
    private fun initMiniPlayer() {
        val miniPlayer = MiniPlayerFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.containerPlayer, miniPlayer)
            .commit()

        findViewById<FrameLayout>(R.id.containerPlayer).setOnClickListener {
            var song = currentSong
            if (song != null) {
                openBigPlayer(song)
            }
        }

        /** TODO: For some reason, this findViewById call will crash the application*/
//        findViewById<ImageButton>(R.id.ivShuffle).setOnClickListener {
//            // perform shuffle code
//        }
    }
}
