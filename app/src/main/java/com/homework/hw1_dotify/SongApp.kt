package com.homework.hw1_dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import kotlin.random.Random

class SongApp(var listOfSongs: List<Song>): Application() {
    var songPlays = mutableMapOf<String, Int>()
    var currentSong: Song? = null

    fun initSongPlays() {
        for (song in listOfSongs) {
            songPlays[song.id] = Random.nextInt(1, 1001)
        }
    }

    fun playOnce(songID: String) {
        var currentPlays = songPlays[songID]
        if (currentPlays != null) {
            songPlays[songID] = currentPlays + 1
        }
    }

    fun getPlays(songID: String): Int? {
        return songPlays[songID]
    }
}