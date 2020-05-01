package com.homework.hw1_dotify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() { // TODO: how to make this implement an app AND the Interface at the same time?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // create the Fragments and bind them to the page
        initSongList()

        // example of how we'll shuffle the list
        shuffleButton.setOnClickListener {
            val songList = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as SongListFragment
            songList.shuffle() // private function that performs the shuffling

        }
    }

    private fun initSongList() {
        if (supportFragmentManager.findFragmentByTag(SongListFragment.TAG) == null) {
            // do the initializing
        } else {
            // fragment already exists
        }
        val songList = SongListFragment() // how to pass a List<Song> to the RecyclerView Fragment
        val argumentBundle = Bundle().apply {
            val email = listOfSongs // get listOfSongs from the EricChee api call
            putParcelable(SongListFragment.arg_songlist, email)
        }

        songList.arguments = argumentBundle
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameMain, songList)
            .addToBackStack(SongListFragment.TAG)
            .commit()

        // apply click listener to the songs?
//        findViewById<FrameLayout>(R.id.frameMain).setOnClickListener {
//            var song = currentSong
//            if (song != null) {
//                openBigPlayer(song)
//            }
//        }
    }
}