package com.homework.hw1_dotify

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.homework.hw1_dotify.MainActivity.Companion.SONG_ARTIST
import com.homework.hw1_dotify.MainActivity.Companion.SONG_IMG
import com.homework.hw1_dotify.MainActivity.Companion.SONG_PLAYS
import com.homework.hw1_dotify.MainActivity.Companion.SONG_TITLE
import kotlinx.android.synthetic.main.mini_player.*
import java.lang.reflect.Type
import java.net.URL

class SongListActivity : AppCompatActivity() {
    private val songURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"
    private lateinit var songApp: SongApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        /** retrieve and store songs */
        queryForSongs() // this is the API call
        val listOfSongs = SongDataProvider.getAllSongs() // this will be an API call later
        songApp = SongApp(listOfSongs)
        songApp.initSongPlays()

        /** init Views */
        initRecyclerView()
        initMiniPlayer()
    }

    // TODO: perform HTTP request to Song List API
    // TODO: convert results into List<Song>
    // TODO: return listOfSongs
//    private fun queryForSongs() { // using URL()
//        val task = Runnable {
//            val songsJSON = URL("https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json").readText()
//            runOnUiThread {
//                Log.i("patrin", songsJSON)
//            }
//        }
//
//        val backgroundThread = Thread(task)
//        backgroundThread.start()
//    }

    private fun queryForSongs(): List<Song> { // using Volley
        var listOfSongs: List<Song> = emptyList()
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, songURL,
            { response ->
                val gson = Gson()
                val convertedResponse = gson.fromJson(response, MusicLibrary::class.java)
                listOfSongs = convertedResponse.songs
            },
            { error ->
                // TODO: display a proper error of some kind
                Log.e("patrin", "Error occurred: ${error.networkResponse.statusCode}")
            })
        queue.add(request)
        return listOfSongs
    }

    private fun initRecyclerView() {
        val songListAdapter = SongListAdapter(songApp.listOfSongs)
        songListAdapter.onSongClickListener = {
            songApp.currentSong = it
            songApp.playOnce(it.id)
            findViewById<TextView>(R.id.tvCurrentSong).text = it.title + " - " + it.artist
        }

        findViewById<RecyclerView>(R.id.rvSongList).adapter = songListAdapter
    }

    private fun openBigPlayer(song: Song) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra(SONG_TITLE, song.title)
        intent.putExtra(SONG_ARTIST, song.artist)
        intent.putExtra(SONG_IMG, song.largeImageID)
        intent.putExtra(SONG_PLAYS, songApp.getPlays(song.id))
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
            var song = songApp.currentSong
            if (song != null) {
                openBigPlayer(song)
            }
        }

        /** TODO: For some reason, this findViewById call will crash the application. Same deal if I try findViewById */
//        ivShuffle.setOnClickListener {
//
//        }
    }
}

data class MusicLibrary (
    val songs: List<Song>
)