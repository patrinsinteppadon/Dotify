package com.homework.hw1_dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class SongListAdapter(initialListOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    private var listOfSongs: List<Song> = initialListOfSongs.toList()   // create new instance of list
    var onSongClickListener: ((song: Song) -> Unit)? = null             // lateinit of behavior for when a song is tapped

    /**
     * creates an item_song view.
     * returns the reference for it.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfSongs.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        holder.bind(song, position)
    }

    /** updates the song list with new songs. */
    fun change(newSongList: List<Song>) {
        // Normal way to apply updates to list
        listOfSongs = newSongList
        notifyDataSetChanged()

        // Animated way of applying updates to list
//        val callback = PersonDiffCallback(listOfPeople, newPeople)
//        val diffResult = DiffUtil.calculateDiff(callback)
//        diffResult.dispatchUpdatesTo(this)
//        listOfSongs = newSongList
    }

    /**
     * a ViewHolder, which is a component of RecyclerView. Behaves like a wrapper class.
     * holds onto a single item_song, with a bind function for changing the title, artist and image.
     */
    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)
        private val ivAlbum = itemView.findViewById<ImageView>(R.id.ivAlbum)

        fun bind(song: Song, position: Int) {
            tvTitle.text = song.title
            tvArtist.text = song.artist
            Picasso.get().load(song.smallImageID).into(ivAlbum);

            itemView.setOnClickListener {
                onSongClickListener?.invoke(song)
            }
        }
    }
}
