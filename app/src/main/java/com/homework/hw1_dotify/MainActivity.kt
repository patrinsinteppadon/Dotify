package com.homework.hw1_dotify

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.ericchee.songdataprovider.SongDataProvider

class MainActivity : AppCompatActivity() { // TODO: how to make this implement an app AND the Interface at the same time?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // create the Fragments and bind them to the page
        initSongList()

        // shuffle button
//        findViewById<ImageButton>(R.id.ivShuffle).setOnClickListener {
//            val songList = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as SongListFragment
//            songList.shuffle() // private function that performs the shuffling
//
//        }
    }

    private fun initSongList() {
        if (supportFragmentManager.findFragmentByTag(SongListFragment.TAG) == null) {
            val listOfSongs = SongDataProvider.getAllSongs()
            val fragSongList = SongListFragment.getInstance(listOfSongs) // how to pass a List<Song> to the RecyclerView Fragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frameMain, fragSongList)
                .addToBackStack(SongListFragment.TAG)
                .commit()
        } else {
            // fragment already exists
        }
    }
}