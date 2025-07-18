package com.divyam.nothingchargingcompanion

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val LEVEL_CODES = listOf(101, 102, 103)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        LEVEL_CODES.forEachIndexed { index, requestCode ->
            val btn = Button(this).apply {
                text = "Select Sound for Level ${index + 1}"
                setOnClickListener {
                    selectSoundFile(requestCode)
                }
            }
            layout.addView(btn)
        }

        setContentView(layout)

        startForegroundService(Intent(this, ChargingMonitorService::class.java))
    }

    private fun selectSoundFile(requestCode: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "audio/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data?.data != null) {
            val uri = data.data!!
            contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val prefs = getSharedPreferences("glyph_sounds", MODE_PRIVATE)
            prefs.edit().putString("level$requestCode", uri.toString()).apply()
            Toast.makeText(this, "Sound saved for level", Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
