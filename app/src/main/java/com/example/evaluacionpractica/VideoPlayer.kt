package com.example.evaluacionpractica

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoPlayer = findViewById<VideoView>(R.id.videoView)

        val videoRes = intent.getIntExtra("video_resource",-1)

        videoPlayer.setOnPreparedListener { mp ->
            val videoRatio = mp.videoWidth.toFloat() / mp.videoHeight.toFloat()
            val screenRatio = videoPlayer.width.toFloat() / videoPlayer.height.toFloat()

            if (videoRatio > screenRatio) {
                val params = videoPlayer.layoutParams
                params.height = (videoPlayer.width / videoRatio).toInt()
                videoPlayer.layoutParams = params
            } else {
                val params = videoPlayer.layoutParams
                params.width = (videoPlayer.height * videoRatio).toInt()
                videoPlayer.layoutParams = params
            }
        }

        if (videoRes != -1) {
            val videoUri = Uri.parse("android.resource://${packageName}/${videoRes}")
            videoPlayer.setVideoURI(videoUri)

            val mediaControl = MediaController(this)
            videoPlayer.setMediaController(mediaControl)

            mediaControl.setAnchorView(videoPlayer)

            videoPlayer.start()
        } else {
            Toast.makeText(this, "Error: Video no encontrado", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}