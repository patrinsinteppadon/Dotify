package com.homework.hw1_dotify

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

/**
 * HW #3: The Song List Recycler view, created in HW2.
 * Converted into a Fragment
 */
class SongListFragment: Fragment() {
    companion object {
        val TAG = "TAG_SONG_LIST_FRAGMENT"
        val ARG_SONG_LIST = "ARG LIST OF SONGS"

        fun getInstance(listOfSongs: List<Song>): SongListFragment { // use this to pass arguments to Fragment
            var instance = SongListFragment()
            instance.arguments = Bundle().apply {
                putParcelableArrayList(ARG_SONG_LIST, ArrayList(listOfSongs))
            }
            return instance
        }
    }
    private lateinit var initialSongs: List<Song>
    private lateinit var songListAdapter: SongListAdapter
    private var onSongClickedListener: OnSongClickedListener? = null // TODO: init interface

    /** Section 1 ============ Fragment Lifecycle */
    // runs when the fragment is attached to activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSongClickedListener ) {
            onSongClickedListener = context
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()

        // get the song data from args
        arguments?.getParcelableArrayList<Song>(ARG_SONG_LIST)?.let {
            initialSongs = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_songlist, container, false)
    }

    /** Section 2 ============ Private functions */



    private fun initRecyclerView() {
        val listOfSongs = SongDataProvider.getAllSongs() // TODO: listOfSongs becomes a parameter for the Fragment
        val songListAdapter = SongListAdapter(listOfSongs)
        songListAdapter.onSongClickListener = {
            // update the Activity with the value of "it"
        }

        findViewById<RecyclerView>(R.id.rvSongList).adapter = songListAdapter // TODO: this occurs in Activity
    }

    // perform shuffle behavior
    fun shuffle() {
        songListAdapter.addEmail(Email("content", "author"))
    }


}

interface OnSongClickedListener {
    fun onSongClicked(song: Song) {
        // returns nothing
    }
}