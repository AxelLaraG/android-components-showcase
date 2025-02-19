package com.example.evaluacionpractica

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val btnReturn = findViewById<ImageButton>(R.id.btnBack)
        val videoPlayer = findViewById<VideoView>(R.id.videoView)
        val videoRes = intent.getIntExtra("video_resource",-1)

        btnReturn.setOnClickListener {
            finish()
        }

        if (videoRes != -1) {
            val videoUri = Uri.parse("android.resource://${packageName}/${videoRes}")
            videoPlayer.setVideoURI(videoUri)

            val mediaControl = MediaController(this)
            videoPlayer.setMediaController(mediaControl)
            videoPlayer.setOnPreparedListener{
                hideNotificationBar()
            }
            videoPlayer.setOnCompletionListener {
                showNotificationBar()
            }

            mediaControl.setAnchorView(videoPlayer)

            videoPlayer.start()
        } else {
            Toast.makeText(this, "Error: Video no encontrado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun hideNotificationBar(){
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    private fun showNotificationBar(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

}