package com.divyam.nothingchargingcompanion

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

object GlyphSoundController {
    private var player: MediaPlayer? = null

    fun playSoundForLevel(context: Context, levelCode: Int) {
        stop()
        val uriString = context.getSharedPreferences("glyph_sounds", Context.MODE_PRIVATE)
            .getString("level$levelCode", null) ?: return

        val uri = Uri.parse(uriString)
        player = MediaPlayer().apply {
            setDataSource(context, uri)
            isLooping = true
            prepare()
            start()
        }
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}
