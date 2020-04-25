package com.homework.hw1_dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UltimateSongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultimate_song_list)

        val songList = SongListActivity()
        val miniPlayer = MiniPlayerFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.containerList, miniPlayer)
            .commit()
    }
}
